package apis.loader;

import apis.ApiHandler;
import dataTypes.TextFile;
import dataTypes.Texture;

public class Loader {
	static LoaderType loaderObject=ApiHandler.getLoader();
	
	public static TextFile loadFile(String path){
		return loaderObject.loadFile(path);
	}
	
	public static Texture loadImage(String filename){
		return loaderObject.loadImage(filename);
	}
	
	public static void cleanup(){
		loaderObject.cleanup();
	}
}
