package apis.shaderManager;

import dataTypes.Shader;
import math.Matrix4;

public class NullShader implements ShaderType {
	@Override
	public Shader load(String path){return null;}

	@Override
	public void cleanup(){}

	@Override
	public void loadVariable(String name, Shader shader, float value){}
	
	@Override
	public void loadVariable(String name, Shader shader, Matrix4 value){}
}
