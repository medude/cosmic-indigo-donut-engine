package apis.loader;

import apis.ApiHandler;
import dataTypes.ConfigData;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
import scene.Scene;

public class Loader {
	private static LoaderType loaderObject = ApiHandler.getLoader();
	private static ConfigData data = null;

	public static TextFile loadFile(String filename) throws MalformedFileException {
		return loaderObject.loadFile(filename);
	}

	public static Texture loadImage(String filename) throws MalformedFileException {
		return loaderObject.loadImage(filename);
	}

	public static void cleanup() {
		loaderObject.cleanup();
	}

	public static ModelData loadOBJ(String filename) throws MalformedFileException {
		return loaderObject.loadOBJ(filename);
	}

	public static Scene loadScene(String filename) throws MalformedFileException {
		return loaderObject.loadScene(filename);
	}

	public static void loadTextures() throws MalformedFileException {
		loaderObject.loadTextures();
	}

	public static Texture getTexture(int textureIndex) {
		return loaderObject.getTexture(textureIndex);
	}

	public static void loadShaders() {
		loaderObject.loadShaders();
	}

	public static Shader getShader(int shaderIndex) {
		return loaderObject.getShader(shaderIndex);
	}

	public static void loadModels() throws MalformedFileException {
		loaderObject.loadModels();
	}

	public static ModelData getModel(int modelIndex) {
		return loaderObject.getModel(modelIndex);
	}

	public static ConfigData loadConfig(String url) {
		data = loaderObject.loadConfig(url);
		return data;
	}

	public static ConfigData getconfigData() {
		return data;
	}
}
