package components;

import java.util.HashMap;

public class Thing {
	private int id;
	private HashMap<String, Component> components;
	
	public Thing(int id){
		this.id=id;
	}

	public int getID(){
		return id;
	}
	
	public void addComponent(Component component){
		components.put(component.getName(), component);
	}
	
	public Component getComponent(String name){
	return components.get(name);
	}
	
	public boolean isComponent(String name){
		return components.containsKey(name);
	}
}
