package apis.loader.scene;

import apis.loader.Loader;
import components.Component;
import components.ComponentType;
import dataTypes.AnyType;
import dataTypes.TextFile;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.Json;
import externalLibraries.minimalJson.main.JsonObject;
import math.TransformationMatrix;
import math.Vector3;
import scene.Node;

/**
 * This class is in charge of loading scenes from native java methods and MinimalJson
 * 
 * @author medude
 */
public class JavaSceneLoader {
	/**
	 * This method is called to get a scene.
	 * 
	 * @param filename
	 *            This is the filename relative to the /res directory
	 * @return Returns parent node containing all elements of the specified scene
	 * @throws MalformedFileException
	 *             If the file is malformed and cannot be understood, this is thrown
	 */
	public Node loadScene(String filename) throws MalformedFileException {
		TextFile file = Loader.loadFile(filename);

		String jsonText = "";

		for (String line : file.getLines()) {
			jsonText += line + "\n";
		}

		JsonObject json = Json.parse(jsonText).asObject();
		JsonObject node = json;

		Node scene = new Node();

		scene.addData("name", new AnyType<Object>(node.get("name").asString()));

		scene.addComponent(
				new Component(new AnyType<Object>(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)),
				ComponentType.GLOBAL_TRANSFORM));

		processNode(node, null, scene);

		return scene;
	}

	private void processNode(JsonObject node, Node lastNode, Node scene) {
		Node processedNode = new Node();

		// Get the components
		for (int i = 0; i < node.get("components").asArray().size(); i++) {
			JsonObject currentComponent = node.get("components").asArray().get(i).asObject();

			switch (currentComponent.get("type").asString()) {

			// If it's a RenderComponent
			case "RenderComponent":
				processedNode.addComponent(new Component(ComponentType.RENDER));
				break;

			// If it's a ModelComponent
			case "ModelComponent":
				processedNode.addComponent(
						new Component(Loader.getModel(currentComponent.get("modelIndex").asInt()),
								ComponentType.MODEL));
				break;

			// If it's a ShaderComponent
			case "ShaderComponent":
				processedNode.addComponent(
						new Component(Loader.getShader(currentComponent.get("shaderIndex").asInt()),
								ComponentType.SHADER));
				break;

			// If it's a TextureComponent
			case "TextureComponent":
				processedNode.addComponent(
						new Component(Loader.getTexture(currentComponent.get("textureIndex").asInt()),
								ComponentType.TEXTURE));
				break;

			// If it's a TransformComponent
			case "TransformComponent":
				Vector3 position = new Vector3(currentComponent.get("positionX").asDouble(),
						currentComponent.get("positionY").asDouble(), currentComponent.get("positionZ").asDouble());
				
				Vector3 rotation = new Vector3(currentComponent.get("rotX").asDouble(), currentComponent.get("rotY").asDouble(),
						currentComponent.get("rotZ").asDouble());
				
				Float scale = currentComponent.get("scale").asFloat();

				processedNode.addComponent(new Component((TransformationMatrix.create(position, rotation, scale)),
								ComponentType.LOCAL_TRANSFORM));

				processedNode.addComponent(new Component(ComponentType.GLOBAL_TRANSFORM));
				break;
			}
		}
		if (node.get("children").asArray().size() != 0 && lastNode != null) {
			lastNode.children[node.get("children").asArray().size() - 1] = processedNode;
		}

		lastNode = processedNode;

		for (int i = 0; i < node.get("children").asArray().size(); i++) {
			JsonObject child = node.get("children").asArray().get(i).asObject();

			processNode(child, lastNode, scene);
		}
	}
}
