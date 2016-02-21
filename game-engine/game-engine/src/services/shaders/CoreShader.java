package services.shaders;

public abstract class CoreShader {
	public abstract int load(String path);
	
	public abstract void cleanup();
}
