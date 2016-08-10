package scene;

public class Area extends Node {
	public Area(Node[] children) {
		super("area");
		for (Node child : children) {
			if (!child.isType("group")) {
				throw new IllegalArgumentException(
						"An object of type " + child.getType() + " was assigned as a child of an area.");
			}
		}

		this.children = children;
	}
}
