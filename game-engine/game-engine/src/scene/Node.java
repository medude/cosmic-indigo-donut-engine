package scene;

import java.util.HashMap;

import apis.console.Console;
import components.Component;
import components.types.TransformComponent;
import math.MatrixMath;
import math.TransformationMatrix;
import math.Vector3;

public abstract class Node {	
	public Node[] children = {};
	
	private HashMap <String, Component> components = new HashMap <String, Component>();
	
	private String nodeType;
	
	public boolean dirtyFlag = true;
	
	public Node(String nodeType) {
		this.nodeType = nodeType.toLowerCase();
		addComponent("LocalTransformComponent", new TransformComponent(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
		addComponent("GlobalTransformComponent", new TransformComponent(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
	}
	
	public void calculateChildTransform(boolean forceTrue) {
		for(Node child:children) {
			Console.log("Calculating transform for " + child.getType());
			
			boolean flag = false;
			
			if(child.dirtyFlag || forceTrue) {
				Console.log("Dirty flag true!");
				TransformComponent transformation = new TransformComponent(MatrixMath.dotProduct(
						((TransformComponent) this.getComponent("GlobalTransformComponent")).transform,
						((TransformComponent) child.getComponent("LocalTransformComponent")).transform));
				
				Console.log(((TransformComponent) child.getComponent("LocalTransformComponent")).transform.position.toString());
				
				child.updateComponent("GlobalTransformComponent", transformation);
				
				 flag = true;
				
				child.dirtyFlag = false;
			}
			
			child.calculateChildTransform(flag);
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
		this.dirtyFlag = true;
	}
	
	public void addComponent(Component component) {
		components.put(component.getName(), component);
		this.dirtyFlag = true;
	}
	
	public void addComponent(String componentName, Component component) {
		components.put(componentName, component);
		this.dirtyFlag = true;
	}
	
	public void removeComponent(String name) {
		components.remove(name);
		this.dirtyFlag = true;
	}
	
	public Component getComponent(String name) {
		return components.get(name);
	}
	
	public boolean isComponent(String name) {
		return components.containsKey(name);
	}
}
