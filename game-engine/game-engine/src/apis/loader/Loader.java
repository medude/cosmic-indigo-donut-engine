package apis.loader;

import apis.ApiHandler;
import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;

public class Loader {
	private static LoaderType loaderObject=ApiHandler.getLoader();
	
	public static TextFile loadFile(String filename){
		return loaderObject.loadFile(filename);
	}
	
	public static Texture loadImage(String filename){
		return loaderObject.loadImage(filename);
	}
	
	public static void cleanup(){
		loaderObject.cleanup();
	}
	
	public static ModelData loadOBJ(String filename){
		return loaderObject.loadOBJ(filename);
	}
}
