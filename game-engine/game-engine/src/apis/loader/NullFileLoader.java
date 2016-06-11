package apis.loader;

import dataTypes.TextFile;
import dataTypes.Texture;

public class NullFileLoader implements LoaderType {
	@Override
	public TextFile loadFile(String path){return null;}

	@Override
	public Texture loadImage(String filename){return null;}

	@Override
	public void cleanup(){}
}