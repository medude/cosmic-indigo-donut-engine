package scene;

import components.types.TransformComponent;
import math.TransformationMatrix;
import math.Vector3;

public class Group extends Node {
	public Group(Vector3 position, Node[] children) {
		super("group");
		localTransform = new TransformComponent(TransformationMatrix.create(position, new Vector3(0, 0, 0), 1));
		worldTransform = new TransformComponent(TransformationMatrix.create(position, new Vector3(0, 0, 0), 1));
		this.children = children;
	}
}