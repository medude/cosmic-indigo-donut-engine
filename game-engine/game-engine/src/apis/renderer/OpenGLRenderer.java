package apis.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import apis.loader.Loader;
import apis.shaderManager.ShaderManager;
import apis.windowManager.WindowManager;
import components.Component;
import components.ComponentType;
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
		scene.addComponent(new Component(
				TransformationMatrix.create(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1),
				ComponentType.GLOBAL_TRANSFORM));

		currentScene = scene;

		iterateChildren(currentScene.children);
	}

	private void iterateChildren(Node[] children) {
		for (Node child : children) {
			if (child == null) {
				continue;
			}
			if (child.hasComponent(ComponentType.RENDER)) {
				if (!child.hasComponent(ComponentType.GLOBAL_TRANSFORM)) {
					child.addComponent(new Component(
							TransformationMatrix.create(new Vector3(0), new Vector3(0), 1),
							ComponentType.GLOBAL_TRANSFORM));
				}

				if (!child.hasComponent(ComponentType.LOCAL_TRANSFORM)) {
					child.addComponent(new Component(
							TransformationMatrix.create(new Vector3(0), new Vector3(0), 1),
							ComponentType.LOCAL_TRANSFORM));
				}

				if (!child.hasComponent(ComponentType.SHADER)) {
					child.addComponent(new Component(Loader.getShader(0), ComponentType.SHADER));
				}

				if (!child.hasComponent(ComponentType.TEXTURE)) {
					child.addComponent(new Component(Loader.getTexture(0), ComponentType.TEXTURE));
				}

				if (!child.hasComponent(ComponentType.MODEL)) {
					child.addComponent(new Component(Loader.getModel(0), ComponentType.MODEL));
				}

				things.add((Thing) child);
			} else if (child.children.length != 0) {
				iterateChildren(child.children);
			}
		}
	}

	@Override
	public void render() {
		for (Thing thing : things) {
			ModelData data = (ModelData) thing.getComponent(ComponentType.MODEL).getData(0).data();
			Shader shader = (Shader) thing.getComponent(ComponentType.SHADER).getData(0).data();

			GL20.glUseProgram(shader.getID());

			ShaderManager.loadVariable("projection", shader, projectionMatrix);

			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(2);

			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,
					((Shader) thing.getComponent(ComponentType.TEXTURE).getData(0).data()).getID());
			ShaderManager.loadVariable("transformation", shader,
					((Matrix4) thing.getComponent(ComponentType.GLOBAL_TRANSFORM).getData(0).data()));

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