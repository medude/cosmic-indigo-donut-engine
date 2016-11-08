/**
 * 
 */
package scene;

import java.util.HashMap;

/**
 * @author medude
 *
 */
public class Scene {
	private SceneNode sceneGraph;

	private HashMap<String, SceneNode> nodeById = new HashMap<String, SceneNode>();
	private HashMap<String, SceneNode> nodeByName = new HashMap<String, SceneNode>();
	private HashMap<String, SceneNode> nodeByClasses = new HashMap<String, SceneNode>();

	public Scene(SceneNode node) {
		sceneGraph = node;

		extractChildren(node);
	}

	private void extractChildren(SceneNode node) {
		if (((SceneNode[]) node.getData("children").getData()).length != 0) {
			
			SceneNode[] children = (SceneNode[]) node.getData("children").getData();

			for (int i = 0; i < children.length; i++) {
				if (children[i].getId() != null) {
					nodeById.put(children[i].getId(), children[i]);
				}

				if (children[i].getName() != null) {
					nodeByName.put(children[i].getName(), children[i]);
				}

				if (children[i].getClasses() != null) {
					for (String classString : children[i].getClasses()) {

						nodeByClasses.put(classString, children[i]);
					}
				}
			}
		}
	}

	public SceneNode getNodeById(String id) {
		return nodeById.get(id);
	}

	public SceneNode getNodeByName(String name) {
		return nodeByName.get(name);
	}

	/*
	 * public SceneNode[] getNodesByClass(String classString) { return nodeByClasses.get(classString); }
	 */

	public SceneNode getSceneGraph() {
		return sceneGraph;
	}

	public void updateSceneGraph(SceneNode newSceneGraph) {
		sceneGraph = newSceneGraph;
	}
}
