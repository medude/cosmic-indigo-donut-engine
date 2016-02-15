package services.loader;

import dataTypes.TextFile;

public abstract class CoreLoader {
	public abstract TextFile loadFile(String path);
}
