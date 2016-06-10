package components.types;

import components.Component;
import dataTypes.Shader;

public class ShaderComponent extends Component {
private Shader shader;
	
	public ShaderComponent(Shader shader){
		super("ShaderComponent");
		this.shader=shader;
	}
	
	public Shader getShader(){
		return shader;
	}
}