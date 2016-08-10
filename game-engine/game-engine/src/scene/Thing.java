package scene;

public class Thing extends Node {
	private int id;

	public Thing(int id) {
		super("thing");
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
