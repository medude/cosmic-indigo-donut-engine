package scene;

import java.util.HashMap;

import apis.errorHandler.ErrorHandler;
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
public class SceneNode {
	/**
	 * This holds all of the data for the node- children, components, or anything else that is needed. There are special
	 * keys that map to important things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 */
	private HashMap<String, AnyType<?>> nodeData;

	/**
	 * This holds if anything has changed, causing something else to be updated (i.e. change local position requires
	 * change in global).
	 */
	public boolean dirtyFlag = true;

	/**
	 * Node constructor- creates Node...
	 * 
	 * @param nodeData
	 *            Instead of using methods to add the node data, just pass it here.
	 */
	public SceneNode(HashMap<String, AnyType<?>> nodeData) {
		this.nodeData = nodeData;
	}

	/**
	 * Node constructor- creates Node...
	 */
	public SceneNode() {
		this.nodeData = new HashMap<String, AnyType<?>>();
	}

	/**
	 * This method calculates the global transform of the children of the called-on SceneNode based on said SceneNode
	 * global position and the children's local transforms. This method also uses the dirty flag for speed. Call this on
	 * the root node and it will calculate the entire scene graph for you.
	 * 
	 * @param forceRecalc
	 *            This is a flag that forces all child SceneNodes to have their transforms calculated, regardless of
	 *            their dirty flag. This can be used to force an update or, as the method uses it, as a quick hack the
	 *            set the dirty flag of all child SceneNodes without actually setting them (if you update a parent's
	 *            global, the children's globals must change, too).
	 */
	@SuppressWarnings("unchecked")
	public void calculateChildTransform(boolean forceRecalc) {

		// Test for presence of children- it may have none! If that is the case, the method really does nothing for this SceneNode.
		if (nodeData.containsKey("children")) {

			// List for quick access to children, if needed.
			SceneNode[] nodeChildren = null;

			try {
				nodeChildren = (SceneNode[]) nodeData.get("children").getData();
			} catch (Exception e) {
				ErrorHandler.handle(e, "There is a child array that is not made of nodes. Check your NodeDatas!", true);
			}
			
			// Iterate through the children to update all of them
			for (SceneNode currentChild : nodeChildren) {
				
				// This flag eventually turns into forceRecalc, not the child's dirtyFlag
				boolean willForceRecalc = false;

				// Also, check for the children's components- if the children have no components, this part is skipped.
				if (currentChild.nodeData.containsKey("components")) {
					// Either dirtyFlag or forceRecalc can execute this.
					if (currentChild.dirtyFlag || forceRecalc) {
						HashMap<ComponentType, Component> currNodeComponents = null;
						HashMap<ComponentType, Component> currChildComponents = null;
						
						try {
							currNodeComponents = (HashMap<ComponentType, Component>) nodeData.get("components")
									.getData();
							currChildComponents = (HashMap<ComponentType, Component>) currentChild.nodeData.get("components")
									.getData();
						} catch (Exception e) {
							ErrorHandler.handle(e,
									"There is a components array that is not made of components. Check your NodeDatas!",
									true);
						}

						// The child's global transformation is just the dot product of the parent's global transform and
						// the child's local transform
						Component transformation = new Component(MatrixMath.dotProduct(
								((Matrix4) currNodeComponents.get(ComponentType.GLOBAL_TRANSFORM).getData(0).getData()),
								((Matrix4) currChildComponents.get(ComponentType.LOCAL_TRANSFORM).getData(0)
										.getData())),
								ComponentType.GLOBAL_TRANSFORM);

						// Set the component
						currChildComponents.replace(ComponentType.GLOBAL_TRANSFORM, transformation);

						// Because the global was updated 
						willForceRecalc = true;

						// Child's global position is out of date
						currentChild.dirtyFlag = false;
					}
				}

				currentChild.calculateChildTransform(willForceRecalc);
			}
		}
	}

	/**
	 * This method changes the value of one key. There are special keys that map to important things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 * 
	 * @param key
	 *            The key to identify the value to change.
	 * @param newValue
	 *            The new value.
	 */
	public void updateData(String key, AnyType<?> newValue) {
		nodeData.replace(key, newValue);
		this.dirtyFlag = true;
	}

	/**
	 * This method adds a new key-value pair to the node's data. There are special keys that map to important
	 * things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 * 
	 * @param key
	 *            The key value used to access the value.
	 * @param value
	 *            The value to store.
	 */
	public void addData(String key, AnyType<?> value) {
		nodeData.put(key, value);
		this.dirtyFlag = true;
	}

	/**
	 * This method removes unwanted data from the node's data via the key. There are special keys that map to important
	 * things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 * 
	 * @param key
	 *            The key to identify the value that you want removed.
	 */
	public void removeData(String key) {
		nodeData.remove(key);
		this.dirtyFlag = true;
	}

	/**
	 * This method returns the value associated with the key. There are special keys that map to important things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 * 
	 * @param key
	 *            The key to return the value for.
	 * @return Requested value.
	 */
	public AnyType<?> getData(String key) {
		return nodeData.get(key);
	}

	/**
	 * This method tests to see if there is such a key as the one you pass in. There are special keys that map to
	 * important things:<br>
	 * "components"- This holds all the node's components<br>
	 * "children"- This holds all of the node's children and is the backbone of the scene graph <br>
	 * 
	 * Other special keys may depend on your shaders, logic programming, or something else. If you need exact values,
	 * look at the documentation of the project who you think is using the particular key(s) that you need.
	 * 
	 * @param key
	 *            The key to test for.
	 * @return True for it exists, false for it doesn't.
	 */
	public boolean hasData(String key) {
		return nodeData.containsKey(key);
	}
}
