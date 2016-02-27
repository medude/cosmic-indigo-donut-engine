package services.fileLoader;

import dataTypes.TextFile;
import dataTypes.Texture;

public abstract class CoreFileLoader {
	public abstract TextFile loadFile(String path);
	
	public abstract Texture loadImage(String filename);
	
	public abstract void cleanup();
}
