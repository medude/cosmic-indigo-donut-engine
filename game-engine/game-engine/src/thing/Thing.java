package thing;

public class Thing {
	private ModelData data;
	private int shader;
	
	public Thing(int shader, ModelData data){
		this.data=data;
		this.shader=shader;
	}

	public ModelData getData() {
		return data;
	}

	public int getShader() {
		return shader;
	}
}
