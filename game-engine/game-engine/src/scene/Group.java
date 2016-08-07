package scene;

public class Group extends Node {
	public Group(Node[] children) {
		super("group");
		this.children = children;
	}
}