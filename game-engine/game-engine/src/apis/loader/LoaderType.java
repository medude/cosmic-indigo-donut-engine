package apis.loader;

import apis.config.ConfigData;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.TextFile;
import dataTypes.Texture;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.JsonObject;
import scene.Scene;

public interface LoaderType {
    public TextFile loadFile(String filename) throws MalformedFileException;

    public Texture loadImage(String filename) throws MalformedFileException;

    public void cleanup();

    public ModelData loadOBJ(String filename) throws MalformedFileException;

    public JsonObject loadJSON(String filename) throws MalformedFileException;

    public Scene loadScene(String filename) throws MalformedFileException;

    public void loadTextures(ConfigData data) throws MalformedFileException;

    public Texture getTexture(int textureIndex);

    public void loadShaders(ConfigData data);

    public Shader getShader(int shaderIndex);

    public void loadModels(ConfigData data) throws MalformedFileException;

    public ModelData getModel(int modelIndex);
}
