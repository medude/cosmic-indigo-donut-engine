package apis.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import apis.console.Console;
import apis.loader.Loader;
import apis.shaderManager.ShaderManager;
import apis.windowManager.WindowManager;
import components.types.ModelComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import components.types.TransformComponent;
import components.types.transformComponent.GlobalTransformComponent;
import components.types.transformComponent.LocalTransformComponent;
import dataTypes.ModelData;
import dataTypes.Shader;
import math.Matrix4;
import math.ProjectionMatrix;
import math.TransformationMatrix;
import math.Vector3;
import scene.Node;
import scene.Scene;
import scene.Thing;

public class OpenGLRenderer implements RendererType {
	private Scene currentScene;
	private List<Thing> things = new ArrayList<Thing>();
	private Matrix4 projectionMatrix;

	@Override
	public void init() {
		GL.createCapabilities();

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		GL11.glCullFace(GL11.GL_BACK);

		projectionMatrix = ProjectionMatrix.create();

		GL11.glClearColor(1, 1, 1, 0);
		GL11.glViewport(0, 0, WindowManager.getWidth(), WindowManager.getHeight());
	}

	@Override
	public void add(Scene scene) {
		scene.addComponent(new GlobalTransformComponent(
				TransformationMatrix.create(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1)));

		currentScene = scene;

		iterateChildren(currentScene.children);
	}

	private void iterateChildren(Node[] children) {
		for (Node child : children) {
			if (child.hasComponent("RenderComponent")) {
				if (!child.hasComponent("GlobalTransformComponent")) {
					child.addComponent(new GlobalTransformComponent(
							TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
				}

				if (!child.hasComponent("LocalTransformComponent")) {
					child.addComponent(new LocalTransformComponent(
							TransformationMatrix.create(new Vector3(0), new Vector3(0), 1)));
				}

				if (!child.hasComponent("ShaderComponent")) {
					child.addComponent(new ShaderComponent(Loader.getShader(0)));
				}

				if (!child.hasComponent("TextureComponent")) {
					child.addComponent(new TextureComponent(Loader.getTexture(0)));
				}

				if (!child.hasComponent("ModelComponent")) {
					child.addComponent(new ModelComponent(Loader.getModel(0)));
				}

				things.add((Thing) child);
			} else {
				iterateChildren(child.children);
			}
		}
	}

	@Override
	public void render() {
		for (Thing thing : things) {
			ModelData data = ((ModelComponent) thing.getComponent("ModelComponent")).getModel();
			Shader shader = ((ShaderComponent) thing.getComponent("ShaderComponent")).getShader();

			GL20.glUseProgram(shader.getID());

			ShaderManager.loadVariable("projection", shader, projectionMatrix);

			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(2);

			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,
					((TextureComponent) thing.getComponent("TextureComponent")).getTexture().getID());
			Console.log(thing);
			ShaderManager.loadVariable("transformation", shader,
					((TransformComponent) thing.getComponent("GlobalTransformComponent")).transform);

			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, data.getIndiciesID());

			// Draw the vertices
			GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(2);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

			GL30.glBindVertexArray(0);

			GL20.glUseProgram(0);
		}
	}
}