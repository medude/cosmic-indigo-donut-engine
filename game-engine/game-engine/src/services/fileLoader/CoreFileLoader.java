package services.fileLoader;

import dataTypes.TextFile;

public abstract class CoreFileLoader {
	public abstract TextFile loadFile(String path);
}
