package apis.loader;

import apis.ApiHandler;
import apis.config.ConfigData;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.JsonObject;
import scene.Scene;

public class Loader {
    private static LoaderType loaderObject = ApiHandler.getLoader();

    public static TextFile loadFile(String filename) throws MalformedFileException {
	return loaderObject.loadFile(filename);
    }

    public static Texture loadImage(String filename) throws MalformedFileException {
	return loaderObject.loadImage(filename);
    }

    public static void cleanup() {
	loaderObject.cleanup();
    }

    public static ModelData loadOBJ(String filename) throws MalformedFileException {
	return loaderObject.loadOBJ(filename);
    }

    public static JsonObject loadJSON(String filename) throws MalformedFileException {
	return loaderObject.loadJSON(filename);
    }

    public static Scene loadScene(String filename) throws MalformedFileException {
	return loaderObject.loadScene(filename);
    }

    public static void loadTextures(ConfigData data) throws MalformedFileException {
	loaderObject.loadTextures(data);
    }

    public static Texture getTexture(int textureIndex) {
	return loaderObject.getTexture(textureIndex);
    }

    public static void loadShaders(ConfigData data) {
	loaderObject.loadShaders(data);
    }

    public static Shader getShader(int shaderIndex) {
	return loaderObject.getShader(shaderIndex);
    }

    public static void loadModels(ConfigData data) throws MalformedFileException {
	loaderObject.loadModels(data);
    }

    public static ModelData getModel(int modelIndex) {
	return loaderObject.getModel(modelIndex);
    }
}
