package components.types;

import components.Component;
import math.Vector3;

public class PositionComponent extends Component {
	private Vector3 position;
	
	public PositionComponent(Vector3 position){
		super("ModelComponent");
		this.position=position;
	}
	
	public Vector3 getPosition(){
		return position;
	}
}
