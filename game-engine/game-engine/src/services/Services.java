package services;

import dataTypes.ModelData;
import services.shaders.CoreShader;
import services.shaders.GLSLShader;
import services.shaders.NullShader;

public class Services {
	public static void init(){
		shaders[0]=new GLSLShader();
		shaders[1]=new NullShader();
	}
	
	public static void cleanup(){
		ModelData data=new ModelData(new float[0], new int[0]);
		data.cleanup();
		
		getShader().cleanup();
	}
	
	//////////////////////////////////
	//Shader                        //
	//////////////////////////////////
	private static CoreShader[] shaders=new CoreShader[2];
	
	public static CoreShader getShader(){
		return shaders[0];
	}
}
