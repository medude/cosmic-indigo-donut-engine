package scene;

import java.util.HashMap;

import apis.console.Console;
import components.Component;
import components.types.TransformComponent;
import math.MatrixMath;

public abstract class Node {
    public Node[] children = {};

    private HashMap<String, Component> components = new HashMap<String, Component>();

    private String nodeType;

    public boolean dirtyFlag = true;

    public Node(String nodeType) {
	this.nodeType = nodeType.toLowerCase();
    }

    public void calculateChildTransform(boolean forceTrue) {
	for (Node child : children) {
	    boolean flag = false;

	    if (child.dirtyFlag || forceTrue) {
		Console.log(this.getType());
		Console.log(child.getType());

		TransformComponent transformation = new TransformComponent(MatrixMath.dotProduct(
			((TransformComponent) this.getComponent("GlobalTransformComponent")).transform,
			((TransformComponent) child.getComponent("LocalTransformComponent")).transform));

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

    public void removeComponent(String name) {
	components.remove(name);
	this.dirtyFlag = true;
    }

    public Component getComponent(String name) {
	return components.get(name);
    }

    public boolean hasComponent(String name) {
	return components.containsKey(name);
    }
}
