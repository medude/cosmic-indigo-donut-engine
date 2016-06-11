package apis.loader;

import dataTypes.TextFile;
import dataTypes.Texture;

public interface LoaderType {
	public abstract TextFile loadFile(String path);
	
	public abstract Texture loadImage(String filename);
	
	public abstract void cleanup();
}
