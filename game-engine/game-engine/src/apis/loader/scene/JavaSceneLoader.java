package apis.loader.scene;

import apis.loader.Loader;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.JsonObject;
import externalLibraries.minimalJson.main.JsonValue;
import scene.Area;
import scene.Group;
import scene.Node;
import scene.Scene;
import scene.Thing;

public class JavaSceneLoader {
	public Scene loadScene(String filename) throws MalformedFileException {
		JsonObject json = Loader.loadJSON(filename);
		JsonObject node = json;

		Scene scene = new Scene(new Area[node.get("childCount").asInt()], null, node.get("name").asString());

		processNode(node, null, scene);

		return scene;
	}

	private void processNode(JsonObject node, Node lastNode, Scene scene) {
		if (lastNode == null) {
			lastNode = scene;
		}

		Node processedNode = null;
		
		switch (node.get("type").asString()) {
		case "area":
			processedNode = new Area(new Node[node.get("childCount").asInt()]);
			break;
		case "group":
			processedNode = new Group(new Node[node.get("childCount").asInt()]);
			break;
		case "thing":
			processedNode = new Thing();
		}

		lastNode.children[node.get("childNumber").asInt()] = processedNode;

		lastNode = processedNode;
		
		for (JsonValue childValue : node.get("children").asArray()) {
			JsonObject child = childValue.asObject();
			
			processNode(child, lastNode, scene);
		}
	}
}
