package scene;

import math.Vector3;

public class Group {
	Vector3 position=new Vector3();
	Group[] groupChildren;
	Thing[] thingChildren;
	
	public Group(Vector3 position, Group[] groupChildren, Thing[] thingChildren){
		this.position=position;
		this.groupChildren=groupChildren;
		this.thingChildren=thingChildren;
	}
}