package services.shaders;

public class NullShader extends CoreShader {
	@Override
	public int load(String path){return 0;}

	@Override
	public void cleanup(){}
}
