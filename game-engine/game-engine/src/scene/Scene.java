package scene;

import components.types.TransformComponent;
import dataTypes.Texture;
import math.TransformationMatrix;
import math.Vector3;

public class Scene extends Node {
	public Texture map;
	
	public Scene(Node[] children, Texture map) {
		super("scene");
		this.children = children;
		this.map = map;
		
		this.addComponent(new TransformComponent(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
	}
}
