package scene;

import java.util.HashMap;

import components.Component;
import components.types.TransformComponent;
import math.MatrixMath;

public abstract class Node {
	public TransformComponent localTransform;
	public TransformComponent worldTransform;
	
	public Node[] children = {};
	
	private HashMap <String, Component> components = new HashMap <String, Component>();
	
	private String nodeType;
	
	public boolean dirtyFlag = false;
	
	public Node(String nodeType) {
		this.nodeType = nodeType.toLowerCase();
	}
	
	public void calculateChildTransform() {
		for(Node child:children) {
			if(child.dirtyFlag) {
				child.worldTransform = new TransformComponent(MatrixMath.dotProduct(this.worldTransform.getTransform(),
						child.localTransform.getTransform()));
				
				child.dirtyFlag = false;
			}
			
			child.calculateChildTransform();
		}
	}
	
	public boolean isType(String testType) {
		return testType.toLowerCase() == nodeType;
	}
	
	public String getType() {
		return nodeType;
	}
	
	public void updateComponent(String name, Component updatedComponent) {
		components.replace(name, updatedComponent);
		dirtyFlag = true;
	}
	
	public void addComponent(Component component) {
		components.put(component.getName(), component);
		dirtyFlag = true;
	}
	
	public void removeComponent(String name) {
		components.remove(name);
		dirtyFlag = true;
	}
	
	public Component getComponent(String name) {
		return components.get(name);
	}
	
	public boolean isComponent(String name) {
		return components.containsKey(name);
	}
}
