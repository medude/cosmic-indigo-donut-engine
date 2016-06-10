package services.fileLoader;

import dataTypes.TextFile;
import dataTypes.Texture;

public class NullFileLoader extends CoreFileLoader {
	@Override
	public TextFile loadFile(String path){return null;}

	@Override
	public Texture loadImage(String filename){return null;}

	@Override
	public void cleanup(){}
}