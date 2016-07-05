package scene;

import components.types.TransformComponent;
import math.TransformationMatrix;
import math.Vector3;

public class Group {
	public TransformComponent localTransform;
	public TransformComponent worldTransform;
	public Group[] groupChildren;
	public Thing[] thingChildren;
	
	public Group(Vector3 position, Group[] groupChildren, Thing[] thingChildren){
		localTransform=new TransformComponent(TransformationMatrix.create(position, new Vector3(0, 0, 0), 1));
		worldTransform=new TransformComponent(TransformationMatrix.create(position, new Vector3(0, 0, 0), 1));
		this.groupChildren=groupChildren;
		this.thingChildren=thingChildren;
	}
	
	public void calculateChildTransform(){
		for(Group group:groupChildren){
			group.worldTransform=this.worldTransform;
		}
	}
}