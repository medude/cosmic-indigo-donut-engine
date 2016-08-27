package apis.loader.scene;

import apis.loader.Loader;
import components.types.ModelComponent;
import components.types.RenderComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import components.types.transformComponent.GlobalTransformComponent;
import components.types.transformComponent.LocalTransformComponent;
import exceptions.MalformedFileException;
import externalLibraries.minimalJson.main.JsonObject;
import math.TransformationMatrix;
import math.Vector3;
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

	scene.addComponent(
		new GlobalTransformComponent(TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));

	processNode(node, null, scene);

	return scene;
    }

    private void processNode(JsonObject node, Node lastNode, Scene scene) {
	if (lastNode == null) {
	    lastNode = scene;
	}

	Node processedNode = null;

	// Get the type
	switch (node.get("type").asString()) {
	case "area":
	    processedNode = new Area(new Group[node.get("childCount").asInt()]);
	    break;
	case "group":
	    processedNode = new Group(new Node[node.get("childCount").asInt()]);
	    break;
	case "thing":
	    processedNode = new Thing();
	case "scene":
	    break;
	}

	// Get the components
	for (int i = 0; i < node.get("componentCount").asInt(); i++) {
	    JsonObject currentComponent = node.get("components").asArray().get(i).asObject();

	    switch (currentComponent.get("type").asString()) {

	    // If it's a RenderComponent
	    case "RenderComponent":
		processedNode.addComponent(new RenderComponent());
		break;

	    // If it's a ModelComponent
	    case "ModelComponent":
		processedNode.addComponent(
			new ModelComponent(Loader.getModel(currentComponent.get("modelIndex").asInt())));
		break;

	    // If it's a ShaderComponent
	    case "ShaderComponent":
		processedNode.addComponent(
			new ShaderComponent(Loader.getShader(currentComponent.get("shaderIndex").asInt())));
		break;

	    // If it's a TextureComponent
	    case "TextureComponent":
		    processedNode.addComponent(
			    new TextureComponent(Loader.getTexture(currentComponent.get("textureIndex").asInt())));
		break;
	    
	    // If it's a TransformComponent
	    case "TransformComponent":
		processedNode.addComponent(new LocalTransformComponent(TransformationMatrix
				    .create(new Vector3(currentComponent.get("positionX").asDouble(),
					    currentComponent.get("positionY").asDouble(),
					    currentComponent.get("positionZ").asDouble()),
					    new Vector3(currentComponent.get("rotX").asDouble(),
						    currentComponent.get("rotY").asDouble(),
						    currentComponent.get("rotZ").asDouble()),
					    currentComponent.get("scale").asFloat())));

		processedNode.addComponent(new GlobalTransformComponent(null));
		break;
	    }
	}

	lastNode.children[node.get("childNumber").asInt()] = processedNode;

	lastNode = processedNode;

	for (int i = 0; i < node.get("childCount").asInt(); i++) {
	    JsonObject child = node.get("children").asArray().get(i).asObject();

	    processNode(child, lastNode, scene);
	}
    }
}
