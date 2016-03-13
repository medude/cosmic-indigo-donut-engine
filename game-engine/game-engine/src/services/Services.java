package services;

import dataTypes.ModelData;
import services.console.CoreConsole;
import services.console.JavaConsole;
import services.console.NullConsole;
import services.errorHandler.CoreErrorHandler;
import services.errorHandler.JavaErrorHandler;
import services.errorHandler.NullErrorHandler;
import services.fileLoader.CoreFileLoader;
import services.fileLoader.JavaFileLoader;
import services.fileLoader.NullFileLoader;
import services.objLoader.CoreOBJLoader;
import services.objLoader.HomemadeObjLoader;
import services.objLoader.NullObjLoader;
import services.renderer.CoreRenderer;
import services.renderer.NullRenderer;
import services.renderer.OpenGLRenderer;
import services.shaders.CoreShader;
import services.shaders.GLSLShader;
import services.shaders.NullShader;
import services.window.CoreWindow;
import services.window.GLFWWindow;
import services.window.NullWindow;

public class Services {
	public static void init(){
		consoles[0]=new JavaConsole();
		consoles[1]=new NullConsole();
		
		errorHandlers[0]=new JavaErrorHandler();
		errorHandlers[1]=new NullErrorHandler();
		
		windows[0]=new GLFWWindow();
		windows[1]=new NullWindow();
		
		loaders[0]=new JavaFileLoader();
		loaders[1]=new NullFileLoader();
		
		objLoaders[0]=new HomemadeObjLoader();
		objLoaders[1]=new NullObjLoader();
		
		renderers[0]=new OpenGLRenderer();
		renderers[1]=new NullRenderer();
		
		shaders[0]=new GLSLShader();
		shaders[1]=new NullShader();
	}
	
	public static void cleanup(){
		ModelData data=new ModelData(new float[0], new int[0]);
		data.cleanup();
		
		getShader().cleanup();
		getLoader().cleanup();
	}
	
	//////////////////////////////////
	//Error Handlers                //
	//////////////////////////////////
	private static CoreErrorHandler[] errorHandlers=new CoreErrorHandler[2];
	
	public static CoreErrorHandler getErrorHandler(){
		return errorHandlers[0];
	}
	
	//////////////////////////////////
	//Consoles                      //
	//////////////////////////////////
	private static CoreConsole[] consoles=new CoreConsole[2];
	
	public static CoreConsole getConsole(){
		return consoles[0];
	}
	
	//////////////////////////////////
	//Windows                       //
	//////////////////////////////////
	private static CoreWindow[] windows=new CoreWindow[2];
	
	public static CoreWindow getWindow(){
		return windows[0];
	}
	
	//////////////////////////////////
	//Loaders                       //
	//////////////////////////////////
	private static CoreFileLoader[] loaders=new CoreFileLoader[2];
	
	public static CoreFileLoader getLoader(){
		return loaders[0];
	}
	
	//////////////////////////////////
	//OBJ Loaders                   //
	//////////////////////////////////
	private static CoreOBJLoader[] objLoaders=new CoreOBJLoader[2];
	
	public static CoreOBJLoader getOBJLoader(){
		return objLoaders[0];
	}
	
	//////////////////////////////////
	//Renderer                      //
	//////////////////////////////////
	private static CoreRenderer[] renderers=new CoreRenderer[2];
	
	public static CoreRenderer getRenderer(){
		return renderers[0];
	}
	
	//////////////////////////////////
	//Shader                        //
	//////////////////////////////////
	private static CoreShader[] shaders=new CoreShader[2];
	
	public static CoreShader getShader(){
		return shaders[0];
	}
}
