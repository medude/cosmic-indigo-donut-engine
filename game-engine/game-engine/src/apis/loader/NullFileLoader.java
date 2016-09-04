package apis.loader;

import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
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
	public Scene loadScene(String filename) {
		return null;
	}

	@Override
	public Texture getTexture(int textureIndex) {
		return null;
	}

	@Override
	public Shader getShader(int shaderIndex) {
		return null;
	}

	@Override
	public ModelData getModel(int modelIndex) {
		return null;
	}

	@Override
	public void loadTextures() throws MalformedFileException {
	}

	@Override
	public void loadShaders() {
	}

	@Override
	public void loadModels() throws MalformedFileException {
	}

	@Override
	public dataTypes.ConfigData loadConfig(String url) {
		return null;
	}
}