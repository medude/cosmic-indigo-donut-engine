package thing;

import dataTypes.Shader;
import dataTypes.Texture;

public class Thing {
	private ModelData data;
	private Shader shader;
	private Texture texture;
	
	public Thing(Shader shader, ModelData data, Texture texture){
		this.data=data;
		this.shader=shader;
		this.texture=texture;
	}

	public ModelData getData(){
		return data;
	}

	public Shader getShader(){
		return shader;
	}

	public Texture getTexture() {
		return texture;
	}
}
