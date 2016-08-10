package apis.loader;

import apis.ApiHandler;
import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;

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

	public static void processJSON(String filename) throws MalformedFileException {
		loaderObject.processJSON(filename);
	}
}
