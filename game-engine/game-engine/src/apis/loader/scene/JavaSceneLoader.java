package apis.loader.scene;

import java.util.HashMap;

import apis.console.Console;
import apis.loader.Loader;
import components.Component;
import components.ComponentHelper;
import components.ComponentType;
import dataTypes.AnyType;
import dataTypes.TextFile;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.Json;
import externalLibraries.minimalJson.main.JsonObject;
import math.TransformationMatrix;
import math.Vector3;
import scene.SceneNode;

/**
 * This class is in charge of loading scenes from native java methods and MinimalJson
 * 
 * @author medude
 */
public class JavaSceneLoader {
	/**
	 * This method is called to create and populate a scene.
	 * 
	 * @param filename
	 *            This is the filename relative to the /res directory
	 * @return Returns parent node containing all elements of the specified scene
	 * @throws MalformedFileException
	 *             If the scene file is malformed and cannot be understood, this is thrown
	 */
	public SceneNode loadScene(String filename) throws MalformedFileException {
		TextFile sceneFile = Loader.loadFile(filename);
		String sceneFileContents = "";

		for (String line : sceneFile.getLines()) {
			sceneFileContents += line + "\n";
		}

		JsonObject sceneAsJson = Json.parse(sceneFileContents).asObject();
		JsonObject currJsonSceneNode = sceneAsJson;

		SceneNode gameScene = new SceneNode();

		// This is the element name- it can be used to identify elements- and scenes
		//gameScene.addData("name", new AnyType<String>(currJsonSceneNode.get("name").asString()));

		// These are the components- at the end, they go into the scene's data
		//HashMap<ComponentType, Component> components = new HashMap<ComponentType, Component>();
		
		//components.put(ComponentType.GLOBAL_TRANSFORM,
		//		new Component(new AnyType<Object>(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)),
		//				ComponentType.GLOBAL_TRANSFORM));
		
		//gameScene.addData("components", new AnyType<HashMap<ComponentType, Component>>(components));

		//Console.log(((HashMap<ComponentType, Component>) gameScene.getData("components").getData()).keySet());

		gameScene = processNode(currJsonSceneNode, null);

		return gameScene;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * This method processes each node in the scene graph, adding components, children, and self-calls to add process
	 * children nodes.
	 * 
	 * @param node
	 *            The node to begin processing.
	 * @param lastNode
	 *            The node that was just processed, used to add children, if null it's okay.
	 */
	private SceneNode processNode(JsonObject jsonNode, SceneNode lastNode) {
		SceneNode workingNode = new SceneNode();
		HashMap components = ComponentHelper.createHashMap();

		// Get the components
		for (int i = 0; i < jsonNode.get("components").asArray().size(); i++) {
			JsonObject currentComponent = jsonNode.get("components").asArray().get(i).asObject();

			switch (currentComponent.get("type").asString()) {

			// If it's a RenderComponent
			case "RenderComponent":
				components.put(ComponentType.RENDER, new Component(ComponentType.RENDER));
				break;

			// If it's a ModelComponent
			case "ModelComponent":
				components.put(ComponentType.MODEL, new Component(Loader.getModel(currentComponent.get("modelIndex").asInt()),
						ComponentType.MODEL));
						
				break;

			// If it's a ShaderComponent
			case "ShaderComponent":
				components.put(ComponentType.SHADER, new Component(
						Loader.getShader(currentComponent.get("shaderIndex").asInt()), ComponentType.SHADER));
				break;

			// If it's a TextureComponent
			case "TextureComponent":
				components.put(ComponentType.TEXTURE, new Component(
						Loader.getTexture(currentComponent.get("textureIndex").asInt()), ComponentType.TEXTURE));
				break;

			// If it's a TransformComponent
			case "TransformComponent":
				Vector3 position = new Vector3(currentComponent.get("positionX").asDouble(),
						currentComponent.get("positionY").asDouble(), currentComponent.get("positionZ").asDouble());
				
				Vector3 rotation = new Vector3(currentComponent.get("rotX").asDouble(), currentComponent.get("rotY").asDouble(),
						currentComponent.get("rotZ").asDouble());
				
				Float scale = currentComponent.get("scale").asFloat();

				components.put(ComponentType.LOCAL_TRANSFORM, new Component(
						(TransformationMatrix.create(position, rotation, scale)), ComponentType.LOCAL_TRANSFORM));

				components.put(ComponentType.GLOBAL_TRANSFORM,
						new Component(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1),
								ComponentType.GLOBAL_TRANSFORM));
				break;

			}
		}
		
		workingNode.addData("components", new AnyType<HashMap<ComponentType, Component>>(components));
		workingNode.addData("children", new AnyType(new SceneNode[jsonNode.get("children").asArray().size()]));

		// Add children
		if (lastNode != null) {
			SceneNode[] children = (SceneNode[]) lastNode.getData("children").getData();
			children[jsonNode.get("childNumber").asInt()] = workingNode;
			lastNode.updateData("children", new AnyType(children));
		}

		for (int i = 0; i < jsonNode.get("children").asArray().size(); i++) {
			JsonObject child = jsonNode.get("children").asArray().get(i).asObject();

			processNode(child, workingNode);
			Console.log(i);
		}

		return workingNode;
	}
}
