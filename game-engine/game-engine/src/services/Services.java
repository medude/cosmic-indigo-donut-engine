package services;

import dataTypes.ModelData;
import services.console.CoreConsole;
import services.console.JavaConsole;
import services.console.NullConsole;
import services.errorHandler.CoreErrorHandler;
import services.errorHandler.JavaErrorHandler;
import services.errorHandler.NullErrorHandler;
import services.shaders.CoreShader;
import services.shaders.GLSLShader;
import services.shaders.NullShader;

public class Services {
	public static void init(){
		consoles[0]=new JavaConsole();
		consoles[1]=new NullConsole();
		
		errorHandlers[0]=new JavaErrorHandler();
		errorHandlers[1]=new NullErrorHandler();
		
		shaders[0]=new GLSLShader();
		shaders[1]=new NullShader();
	}
	
	public static void cleanup(){
		ModelData data=new ModelData(new float[0], new int[0]);
		data.cleanup();
		
		getShader().cleanup();
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
	//Shader                        //
	//////////////////////////////////
	private static CoreShader[] shaders=new CoreShader[2];
	
	public static CoreShader getShader(){
		return shaders[0];
	}
}
