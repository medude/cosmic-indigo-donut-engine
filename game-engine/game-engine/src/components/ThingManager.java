package components;

public class ThingManager {
	private static int thingID=0;
	private static int componentID=-1;
	
	public static Thing makeThing(){
		Thing thing=new Thing(thingID);
		thingID++;
		return thing;
	}
	
	public static int getComponentID(){
		componentID++;
		return componentID;
	}
}