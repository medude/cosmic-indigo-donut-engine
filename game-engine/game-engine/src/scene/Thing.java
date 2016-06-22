package scene;

import java.util.HashMap;

import components.Component;

public class Thing {
	private int id;
	private HashMap<String, Component> components=new HashMap<String, Component>();
	
	public Thing(int id){
		this.id=id;
	}

	public int getID(){
		return id;
	}
	
	public void editComponent(String name){
		
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
