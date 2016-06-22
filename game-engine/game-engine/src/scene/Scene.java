package scene;

import dataTypes.Texture;

public class Scene {
	public Area[] children;
	public Texture map;
	
	public Scene(Area[] children, Texture map) {
		this.children = children;
		this.map = map;
	}
}
