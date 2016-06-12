package apis.loader;

import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;

public interface LoaderType {
	public TextFile loadFile(String filename);
	
	public Texture loadImage(String filename);
	
	public void cleanup();
	
	public ModelData loadOBJ(String filename);
}
