package services.objLoader;

import thing.ModelData;

public abstract class CoreOBJLoader {
	public abstract ModelData parse(String filename);
}
