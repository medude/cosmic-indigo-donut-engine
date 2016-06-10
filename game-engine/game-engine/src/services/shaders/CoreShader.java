package services.shaders;

import dataTypes.Shader;
import math.Matrix4;

public abstract class CoreShader {
	public abstract Shader load(String path);
	
	public abstract Shader load(String path, String[] variables);
	
	public abstract void loadVariable(String name, Shader shader, float value);
	
	public abstract void loadVariable(String name, Shader shader, Matrix4 value);
	
	public abstract void cleanup();
}
