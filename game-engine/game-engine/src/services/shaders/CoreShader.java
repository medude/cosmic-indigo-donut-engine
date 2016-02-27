package services.shaders;

import dataTypes.Shader;

public abstract class CoreShader {
	public abstract Shader load(String path);
	
	public abstract Shader load(String path, String[] variables);
	
	public abstract void loadVariable(String name, Shader shader, float value);
	
	public abstract void cleanup();
}
