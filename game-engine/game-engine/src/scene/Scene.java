package scene;

import dataTypes.Texture;

public class Scene {
	Area[] children;
	Texture map;
	
	public Scene(Area[] children, Texture map) {
		this.children = children;
		this.map = map;
	}
}
