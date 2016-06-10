package components;

public abstract class Component {
	private int id;
	private String name;
	
	public Component(String name){
		this.id=ThingManager.getComponentID();
		this.name=name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}