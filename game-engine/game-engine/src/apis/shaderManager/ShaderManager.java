package apis.shaderManager;

import apis.ApiHandler;
import dataTypes.Shader;
import math.Matrix4;

public class ShaderManager {
	private static ShaderType shaderObject=ApiHandler.getShader();
	
	public static Shader load(String path){
		return shaderObject.load(path);
	}
	
	public static void loadVariable(String name, Shader shader, float value){
		shaderObject.loadVariable(name, shader, value);
	}
	
	public static void loadVariable(String name, Shader shader, Matrix4 value){
		shaderObject.loadVariable(name, shader, value);
	}
	
	public static void cleanup(){
		shaderObject.cleanup();
	}
}
