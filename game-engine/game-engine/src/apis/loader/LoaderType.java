package apis.loader;

import dataTypes.ConfigData;
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

    public void loadTextures() throws MalformedFileException;

    public Texture getTexture(int textureIndex);

    public void loadShaders();

    public Shader getShader(int shaderIndex);

    public void loadModels() throws MalformedFileException;

    public ModelData getModel(int modelIndex);

    public ConfigData loadConfig(String url);
}
