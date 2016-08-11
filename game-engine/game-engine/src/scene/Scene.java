package scene;

import dataTypes.Texture;

public class Scene extends Node {
	public Texture map;
	public String name;

	public Scene(Node[] children, Texture map, String name) {
		super("scene");
		this.children = children;
		this.map = map;
		this.name = name;
	}
}
