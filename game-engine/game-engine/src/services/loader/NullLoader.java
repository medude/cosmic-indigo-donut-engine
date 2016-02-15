package services.loader;

import dataTypes.TextFile;

public class NullLoader extends CoreLoader {
	@Override
	public TextFile loadFile(String path){
		return null;
	}
}