package apis.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import apis.shaderManager.ShaderManager;
import apis.windowManager.WindowManager;
import components.Component;
import components.ComponentType;
import dataTypes.ModelData;
import dataTypes.Shader;
import math.Matrix4;
import math.ProjectionMatrix;
import scene.SceneNode;

public class OpenGLRenderer implements RendererType {
	private SceneNode currentScene;
	private List<SceneNode> renderedThings = new ArrayList<SceneNode>();
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
	public void add(SceneNode scene) {
		currentScene = scene;

		iterateChildren((SceneNode[]) currentScene.getData("children").getData());
	}

	@SuppressWarnings("unchecked")
	private void iterateChildren(SceneNode[] children) {
		for (SceneNode child : children) {
			if (child == null) {
				continue;
			}

			if (((HashMap<ComponentType, Component>) child.getData("components").getData())
					.containsKey(ComponentType.RENDER)) {
				renderedThings.add((SceneNode) child);
			} else if (child.hasData("children")) {
				iterateChildren((SceneNode[]) child.getData("children").getData());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void render() {
		for (SceneNode thing : renderedThings) {
			ModelData data = (ModelData) ((HashMap<ComponentType, Component>) thing.getData("components").getData())
					.get(ComponentType.MODEL).getData()[0].getData();
			Shader shader = (Shader) ((HashMap<ComponentType, Component>) thing.getData("components").getData())
					.get(ComponentType.SHADER).getData()[0].getData();

			GL20.glUseProgram(shader.getID());

			ShaderManager.loadVariable("projection", shader, projectionMatrix);

			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(2);

			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D,
					((Shader) ((HashMap<ComponentType, Component>) thing.getData("components").getData())
							.get(ComponentType.TEXTURE).getData()[0].getData()).getID());
			ShaderManager.loadVariable("transformation", shader,
					((Matrix4) ((HashMap<ComponentType, Component>) thing.getData("components").getData())
							.get(ComponentType.GLOBAL_TRANSFORM).getData()[0].getData()));

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