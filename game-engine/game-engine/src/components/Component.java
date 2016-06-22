package components;

import scene.SceneManager;

public abstract class Component {
	private int id;
	private String name;
	
	public Component(String name){
		this.id=SceneManager.getComponentID();
		this.name=name;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}