package apis.loader;

import apis.ApiHandler;
import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.JsonObject;
import scene.Scene;

public class Loader {
	private static LoaderType loaderObject = ApiHandler.getLoader();

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

	public static JsonObject loadJSON(String filename) throws MalformedFileException {
		return loaderObject.loadJSON(filename);
	}
	
	public static Scene loadScene(String filename) throws MalformedFileException {
		return loaderObject.loadScene(filename);
	}
}
