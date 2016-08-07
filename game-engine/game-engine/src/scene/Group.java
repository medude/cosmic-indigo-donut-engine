package scene;

import math.Vector3;

public class Group extends Node {
	public Group(Vector3 position, Node[] children) {
		super("group");
		this.children = children;
	}
}