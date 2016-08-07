package scene;

import dataTypes.Texture;

public class Scene extends Node {
	public Texture map;
	
	public Scene(Node[] children, Texture map) {
		super("scene");
		this.children = children;
		this.map = map;
	}
}
