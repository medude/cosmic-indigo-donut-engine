package scene;

import components.types.TransformComponent;
import math.TransformationMatrix;
import math.Vector3;

public class Area extends Node {
	public Area(Node[] children){
		super("area");
		for(Node child:children){
			if(!child.isType("group")){
				throw new IllegalArgumentException("An object of type " + child.getType() + " was assigned as a child of an area.");
			}
			
			this.addComponent(new TransformComponent(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
		}
		
		this.children=children;
	}
}
