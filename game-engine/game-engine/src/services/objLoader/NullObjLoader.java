package services.objLoader;

import dataTypes.ModelData;

public class NullObjLoader extends CoreOBJLoader {
	@Override
	public ModelData parse(String filename){return new ModelData(null, null);}
}
