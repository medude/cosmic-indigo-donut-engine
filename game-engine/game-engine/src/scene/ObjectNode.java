package scene;

import dataTypes.Thing;

public class ObjectNode extends Node {
	private Thing thing=null;
	
	public ObjectNode(Thing thing){
		this.thing=thing;
	}
	
	public Thing getObject(){
		return thing;
	}
}