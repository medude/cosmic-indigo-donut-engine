package apis.shaderManager;

import dataTypes.Shader;
import math.Matrix4;

public interface ShaderType {
    public Shader load(String path);

    public void loadVariable(String name, Shader shader, float value);

    public void loadVariable(String name, Shader shader, Matrix4 value);

    public void cleanup();
}