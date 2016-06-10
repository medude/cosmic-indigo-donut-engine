package scene;

public class Scene {
	private static ParentNode world=new ParentNode(1);
	
	public static void setup(ObjectNode node){
		world.setChild(0, node);
	}
}
