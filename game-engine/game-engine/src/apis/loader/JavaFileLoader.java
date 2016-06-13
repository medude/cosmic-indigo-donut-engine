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
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

import apis.errorHandle.ErrorHandle;
import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;
import math.Vector2;
import math.Vector3;

public class JavaFileLoader implements LoaderType {
	public List<Integer> images=new ArrayList<Integer>();
	
	@Override
	public TextFile loadFile(String path){
		File file;
		DataInputStream stream;
		BufferedReader read;
		
		try{
			file=new File("res/"+path);
			stream=new DataInputStream(new FileInputStream(file));
			read=new BufferedReader(new InputStreamReader(stream));
			
			List<String> lines=new ArrayList<String>();
			String line;
			
			while((line=read.readLine())!=null){
				lines.add(line);
			}
			
			read.close();
			
			String[] lineList=new String[lines.size()];
			
			for(int i=0; i<lines.size(); i++){
				lineList[i]=lines.get(i);
			}
			
			return new TextFile(lineList);
			
		} catch(IOException e){
			ErrorHandle.handle(e);
		}
		
		return null;
	}
	
	@Override
	public Texture loadImage(String filename){
		final int BYTES_PER_PIXEL=4;
		BufferedImage image=null;
		
		try{
			image=ImageIO.read(new File("res/images/"+filename+".png"));
		}catch(IOException e){
			ErrorHandle.handle(e);
		}
		
		int[] pixels=new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		
		ByteBuffer buffer=BufferUtils.createByteBuffer(image.getWidth()*image.getHeight()*BYTES_PER_PIXEL);
		
		for(int y=0; y<image.getHeight(); y++){
			for(int x=0; x<image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); //R
				buffer.put((byte) ((pixel >> 8) & 0xFF));  //G
				buffer.put((byte) (pixel & 0xFF));         //B
				buffer.put((byte) ((pixel >> 24) & 0xFF)); //A
			}
		}
		
		buffer.flip();
		
		int textureID=GL11.glGenTextures(); //Generate texture ID
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); //Bind texture ID#
		
		//Setup wrap mode
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		//Setup texture scaling filtering
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		images.add(textureID);
		
		return new Texture(textureID);
	}
	
	@Override
	public void cleanup() {
		for(int image:images){
			GL11.glDeleteTextures(image);
		}
	}
	
	@Override
	public ModelData loadOBJ(String filename) {
		FileReader fr=null;
		try {
			fr=new FileReader(new File("res/"+filename+".obj"));
		} catch (FileNotFoundException e) {
			ErrorHandle.handle(e, "File "+filename+".obj was not found in the res directory.", false);
		}
		
		BufferedReader reader=new BufferedReader(fr);
		String line;
		List<Vector3> verticies=new ArrayList<Vector3>();
		List<Vector2> UV=new ArrayList<Vector2>();
		List<Vector3> normals=new ArrayList<Vector3>();
		List<Integer> indicies=new ArrayList<Integer>();
		float[] vertArray=null;
		float[] UVArray=null;
		float[] normalsArray=null;
		int[] indiciesArray=null;
		
		try{
			while(true){
				line=reader.readLine();
				String[] currentLine=line.split(" ");
				if(line.startsWith("v ")){
					Vector3 vertex=new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					verticies.add(vertex);
				}else if(line.startsWith("vt ")){
					Vector2 UVCoord=new Vector2(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
					UV.add(UVCoord);
				}else if(line.startsWith("vn ")){
					Vector3 normal=new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					normals.add(normal);
				}else if(line.startsWith("f ")){
					UVArray=new float[verticies.size()*2];
					normalsArray=new float[verticies.size()*3];
					break;
				}
			}
			
			while(line!=null){
				if(!line.startsWith("f ")){
					line=reader.readLine();
					continue;
				}
				
				String[] currentLine=line.split(" ");
				String[] vertex1=currentLine[1].split("/"); 
				String[] vertex2=currentLine[2].split("/"); 
				String[] vertex3=currentLine[3].split("/"); 
				
				processVertex(vertex1, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex2, indicies, UV, normals, UVArray, normalsArray);
				processVertex(vertex3, indicies, UV, normals, UVArray, normalsArray);
				line=reader.readLine();
			}
			reader.close();
		}catch(Exception e){
			System.err.println("An error has occured while parsing the OBJ file "+filename+".obj.");
			e.printStackTrace();
		}
		
		vertArray=new float[verticies.size()*3];
		indiciesArray=new int[indicies.size()];
		
		int vertexPointer=0;
		for(Vector3 vertex:verticies){
			vertArray[vertexPointer++]=vertex.x;
			vertArray[vertexPointer++]=vertex.y;
			vertArray[vertexPointer++]=vertex.z;
		}
		
		for(int i=0; i<indicies.size(); i++){
			indiciesArray[i]=indicies.get(i);
		}
		
		return new ModelData(vertArray, indiciesArray, normalsArray, UVArray);
	}
	
	private static void processVertex(String[] vertexData, List<Integer> indicies, List<Vector2> UV, List<Vector3> normals, float[] textureArray, float[] normalArray){
		Integer currentVertexPointer=(Integer) (Integer.parseInt(vertexData[0])-1);
		indicies.add(currentVertexPointer);
		
		Vector2 currentTex=UV.get(Integer.parseInt(vertexData[1])-1);
		textureArray[currentVertexPointer*2]=currentTex.x;
		textureArray[currentVertexPointer*2+1]=1-currentTex.y;
		
		Vector3 currentNorm=normals.get(Integer.parseInt(vertexData[2])-1);
		normalArray[currentVertexPointer*3]=currentNorm.x;
		normalArray[currentVertexPointer*3+1]=currentNorm.y;
		normalArray[currentVertexPointer*3+2]=currentNorm.z;
	}
}