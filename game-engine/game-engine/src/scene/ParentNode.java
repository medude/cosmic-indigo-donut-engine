package scene;

public class ParentNode extends Node {
	private Node[] children=null;
	
	public ParentNode(int count){
		this.children=new Node[count];
	}
	
	public ParentNode(Node[] children){
		this.children=children;
	}
	
	public Node[] getChildren(){
		return children;
	}
	
	public void setChild(int index, Node child){
		children[index]=child;
	}
}