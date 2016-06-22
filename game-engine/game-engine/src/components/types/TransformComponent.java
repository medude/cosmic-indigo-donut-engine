package components.types;

import components.Component;
import math.Matrix4;

public class TransformComponent extends Component {
	private Matrix4 transform;
	
	public TransformComponent(Matrix4 transform){
		super("TransformComponent");
		this.transform=transform;
	}
	
	public Matrix4 getTransform(){
		return transform;
	}
}
