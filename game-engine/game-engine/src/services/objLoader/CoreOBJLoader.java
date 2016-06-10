package services.objLoader;

import dataTypes.ModelData;

public abstract class CoreOBJLoader {
	public abstract ModelData parse(String filename);
}
