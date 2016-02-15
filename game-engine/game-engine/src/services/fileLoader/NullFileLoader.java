package services.fileLoader;

import dataTypes.TextFile;

public class NullFileLoader extends CoreFileLoader {
	@Override
	public TextFile loadFile(String path){
		return null;
	}
}