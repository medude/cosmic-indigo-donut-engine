package apis.loader;

import dataTypes.ModelData;
import dataTypes.TextFile;
import dataTypes.Texture;
import externalLibraries.minimalJson.main.JsonObject;
import scene.Scene;

public class NullFileLoader implements LoaderType {
	@Override
	public TextFile loadFile(String path) {
		return null;
	}

	@Override
	public Texture loadImage(String filename) {
		return null;
	}

	@Override
	public void cleanup() {
	}

	@Override
	public ModelData loadOBJ(String filename) {
		return null;
	}

	@Override
	public JsonObject loadJSON(String filename) {
		return null;
	}

	@Override
	public Scene loadScene(String filename) {
		return null;
	}
}