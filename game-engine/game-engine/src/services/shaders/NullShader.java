package services.shaders;

import dataTypes.Shader;
import math.Matrix4;

public class NullShader extends CoreShader {
	@Override
	public Shader load(String path){return null;}

	@Override
	public void cleanup(){}

	@Override
	public void loadVariable(String name, Shader shader, float value){}
	
	@Override
	public void loadVariable(String name, Shader shader, Matrix4 value){}
	
	@Override
	public Shader load(String path, String[] variables){return null;}
}
