package services.objLoader;

import thing.ModelData;

public class NullObjLoader extends CoreOBJLoader {
	@Override
	public ModelData parse(String filename){return new ModelData(new float[0]);}
}
