package apis.loader;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

import apis.console.Console;
import apis.errorHandler.ErrorHandler;
import apis.loader.config.JavaConfigLoader;
import apis.loader.scene.JavaSceneLoader;
import apis.shaderManager.ShaderManager;
import dataTypes.AnyType;
import dataTypes.ConfigData;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
import math.Vector2;
import math.Vector3;
import scene.SceneNode;

public class JavaFileLoader implements LoaderType {
	private JavaSceneLoader sceneLoader = new JavaSceneLoader();
	private JavaConfigLoader configLoader = new JavaConfigLoader();

	public List<Integer> images = new ArrayList<Integer>();

	// Hash map for storing all textures
	HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();

	// Hash map for storing all shaders
	HashMap<Integer, Shader> shaders = new HashMap<Integer, Shader>();

	// Hash map for storing all models
	HashMap<Integer, ModelData> models = new HashMap<Integer, ModelData>();

	// Loop loads in all models
	@Override
	public void loadModels() throws MalformedFileException {
		@SuppressWarnings("unchecked")
		AnyType<Object>[] array = (AnyType<Object>[]) Loader.getconfigData().data.get("models").getData();

		for (int i = 0; i < array.length; i++) {
			models.put(i, Loader.loadOBJ((String) array[i].getData()));
			Console.log("Loaded model " + (i + 1) + "/" + array.length);
		}
	}

	@Override
	public ModelData getModel(int modelIndex) {
		return models.get(modelIndex);
	}

	// Loop loads in all shaders
	@Override
	public void loadShaders() {
		@SuppressWarnings("unchecked")
		AnyType<Object>[] array = (AnyType<Object>[]) Loader.getconfigData().data.get("shaders").getData();

		for (int i = 0; i < array.length; i++) {
			shaders.put(i, ShaderManager.load((String) array[i].getData()));
			Console.log("Loaded shader " + (i + 1) + "/" + array.length);
		}
	}

	@Override
	public Shader getShader(int shaderIndex) {
		return shaders.get(shaderIndex);
	}

	@Override
	public void loadTextures() throws MalformedFileException {
		// Loop loads in all textures
		//              Config data from loader-  find "textures"-  use as array
		@SuppressWarnings("unchecked")
		AnyType<Object>[] array = (AnyType<Object>[]) Loader.getconfigData().data.get("textures").getData();

		for (int i = 0; i < array.length; i++) {
			textures.put(i, Loader.loadImage((String) array[i].getData()));
			Console.log(
					"Loaded texture " + (i + 1) + "/" + array.length);
		}
	}

	@Override
	public Texture getTexture(int textureIndex) {
		return textures.get(textureIndex);
	}

	@Override
	public TextFile loadFile(String path) throws MalformedFileException {
		File file;
		DataInputStream stream;
		BufferedReader read;

		try {
			file = new File("res/" + path);
			stream = new DataInputStream(new FileInputStream(file));
			read = new BufferedReader(new InputStreamReader(stream));

			ArrayList<String> lines = new ArrayList<String>();
			String line;

			while ((line = read.readLine()) != null) {
				lines.add(line);
			}

			read.close();

			String[] lineList = new String[lines.size()];

			for (int i = 0; i < lines.size(); i++) {
				lineList[i] = lines.get(i);
			}

			return new TextFile(lineList);

		} catch (IOException e) {
			ErrorHandler.handle(e);
		}

		return null;
	}

	@Override
	public Texture loadImage(String filename) throws MalformedFileException {
		final int BYTES_PER_PIXEL = 4;
		BufferedImage image = null;

		try {
			image = ImageIO.read(new File("res/images/" + filename));
		} catch (IOException e) {
			ErrorHandler.handle(e);
		}

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // G
				buffer.put((byte) (pixel & 0xFF)); // B
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // A
			}
		}

		buffer.flip();

		int textureID = GL11.glGenTextures(); // Generate texture ID
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); // Bind texture ID

		// Setup wrap mode
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

		// Setup texture scaling filtering
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

		images.add(textureID);

		return new Texture(textureID);
	}

	@Override
	public void cleanup() {
		for (int image : images) {
			GL11.glDeleteTextures(image);
		}
	}

	@Override
	public ModelData loadOBJ(String filename) throws MalformedFileException {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("res/models/" + filename));
		} catch (FileNotFoundException e) {
			ErrorHandler.handle(e, "File " + filename + " was not found in the res/models directory.", false);
		}

		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vector3> verticies = new ArrayList<Vector3>();
		List<Vector2> UV = new ArrayList<Vector2>();
		List<Vector3> normals = new ArrayList<Vector3>();
		List<Integer> indicies = new ArrayList<Integer>();
		double[] vertArray = null;
		double[] UVArray = null;
		double[] normalsArray = null;
		int[] indiciesArray = null;

		try {
			while (true) {
				line = reader.readLine();
				String[] currentLine = line.split(" ");
				if (line.startsWith("v ")) {
					Vector3 vertex = new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
							Float.parseFloat(currentLine[3]));
					verticies.add(vertex);
				} else if (line.startsWith("vt ")) {
					Vector2 UVCoord = new Vector2(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
					UV.add(UVCoord);
				} else if (line.startsWith("vn ")) {
					Vector3 normal = new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
							Float.parseFloat(currentLine[3]));
					normals.add(normal);
				} else if (line.startsWith("f ")) {
					UVArray = new double[verticies.size() * 2];
					normalsArray = new double[verticies.size() * 3];
					break;
				}
			}

			while (line != null) {
				if (!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}

				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");

				processVertex(vertex1, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex2, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex3, indicies, UV, normals, UVArray, normalsArray);
				line = reader.readLine();
			}

			reader.close();
		} catch (Exception e) {
			System.err.println("An error has occured while parsing the OBJ file " + filename + ".obj.");
			e.printStackTrace();
		}

		vertArray = new double[verticies.size() * 3];
		indiciesArray = new int[indicies.size()];

		int vertexPointer = 0;
		for (Vector3 vertex : verticies) {
			vertArray[vertexPointer++] = vertex.x;
			vertArray[vertexPointer++] = vertex.y;
			vertArray[vertexPointer++] = vertex.z;
		}

		for (int i = 0; i < indicies.size(); i++) {
			indiciesArray[i] = indicies.get(i);
		}

		return new ModelData(vertArray, indiciesArray, normalsArray, UVArray);
	}

	private void processVertex(String[] vertexData, List<Integer> indicies, List<Vector2> UV, List<Vector3> normals,
			double[] textureArray, double[] normalArray) {
		Integer currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indicies.add(currentVertexPointer);

		Vector2 currentTex = UV.get(Integer.parseInt(vertexData[1]) - 1);
		textureArray[currentVertexPointer * 2] = currentTex.x;
		textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;

		Vector3 currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalArray[currentVertexPointer * 3] = currentNorm.x;
		normalArray[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalArray[currentVertexPointer * 3 + 2] = currentNorm.z;
	}

	@Override
	public SceneNode loadScene(String filename) throws MalformedFileException {
		return sceneLoader.loadScene(filename);
	}

	@Override
	public ConfigData loadConfig(String url) {
		return configLoader.loadConfig(url);
	}
}