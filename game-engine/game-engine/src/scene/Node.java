package scene;

import java.util.HashMap;

import apis.errorHandle.ErrorHandle;
import components.Component;
import components.ComponentType;
import dataTypes.AnyType;
import math.Matrix4;
import math.MatrixMath;

/**
 * This class represents a Node in the scene graph.
 * 
 * @author medude
 */
public class Node {
	private HashMap<String, AnyType<?>> nodeData;

	public boolean dirtyFlag = true;

	public Node(HashMap<String, AnyType<?>> nodeData) {
		this.nodeData = nodeData;
	}

	public Node() {
		this.nodeData = new HashMap<String, AnyType<?>>();
	}

	/**
	 * This method calculates the global transform of the children of the called-on Node based on said Node's global
	 * position and the children's local transforms. This method also uses the dirty flag for speed. Call this on the
	 * root node and it will calculate the entire scene graph for you.
	 * 
	 * @param forceTrue
	 *            This is a flag that forces all child Nodes to have their transforms calculated, regardless of their
	 *            dirty flag. This can be used to force an update or, as the method uses it, as a quick hack the set the
	 *            dirty flag of all child Nodes without actually setting them (if you update a parent's global, the
	 *            children's globals must change, too).
	 */
	@SuppressWarnings("unchecked")
	public void calculateChildTransform(boolean forceTrue) {

		// Test for presence of children- it may have none! If that is the case, this method really does nothing for this Node.
		if (nodeData.containsKey("children")) {

			// List for quick access to children, if needed.
			Node[] children = null;

			try {
				children = (Node[]) nodeData.get("children").data();
			} catch (Exception e) {
				ErrorHandle.handle(e, "There is a child array that is not made of nodes. Check your NodeDatas!", true);
			}
			
			// Iterate through the children to update all of them
			for (Node child : children) {
				
				// This flag eventually turns into forceTrue, not the child's dirtyFlag
				boolean flag = false;

				// Also, check for the children's components- again, if the children have no components, this part is skipped.
				if (child.nodeData.containsKey("components")) {
					// Either the dirty flag or forceTrue can execute this.
					if (child.dirtyFlag || forceTrue) {
						HashMap<ComponentType, Component> components = null;
						HashMap<ComponentType, Component> childComponents = null;
						
						try {
							components = (HashMap<ComponentType, Component>) nodeData.get("components").data();
							childComponents = (HashMap<ComponentType, Component>) child.nodeData.get("components")
									.data();
						} catch (Exception e) {
							ErrorHandle.handle(e,
									"There is a components array that is not made of components. Check your NodeDatas!",
									true);
						}

						Component transformation = new Component(MatrixMath.dotProduct(
								((Matrix4) components.get(ComponentType.GLOBAL_TRANSFORM).getData(0).data()),
								((Matrix4) childComponents.get(ComponentType.LOCAL_TRANSFORM).getData(0).data())),
								ComponentType.GLOBAL_TRANSFORM);

						childComponents.replace(ComponentType.GLOBAL_TRANSFORM, transformation);

						flag = true;

						child.dirtyFlag = false;
					}
				}

				child.calculateChildTransform(flag);
			}
		}
	}

	public void updateData(String key, AnyType<?> newValue) {
		nodeData.replace(key, newValue);
		this.dirtyFlag = true;
	}

	public void addData(String key, AnyType<?> value) {
		nodeData.put(key, value);
		this.dirtyFlag = true;
	}

	public void removeData(String key) {
		nodeData.remove(key);
		this.dirtyFlag = true;
	}

	public AnyType<?> getData(String key) {
		return nodeData.get(key);
	}

	public boolean hasData(String key) {
		return nodeData.containsKey(key);
	}
}
