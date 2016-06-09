package services.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import components.Thing;
import components.types.ModelComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import dataTypes.ModelData;
import dataTypes.Shader;
import math.Matrix4;
import math.ProjectionMatrix;
import math.TransformationMatrix;
import math.Vector3;
import services.Services;

public class OpenGLRenderer extends CoreRenderer {
	private List<Thing> things=new ArrayList<Thing>();
	private Matrix4 projectionMatrix;
	
	@Override
	public void init(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		projectionMatrix=ProjectionMatrix.create();
		
		GL11.glClearColor(1, 1, 1, 0);
	}
	
	@Override
	public void add(Thing thing){
		things.add(thing);
	}
	
	private float rot=0;
	
	@Override
	public void render(){
		for(Thing thing:things){
			ModelData data=((ModelComponent) thing.getComponent("ModelComponent")).getModel();
			Shader shader=((ShaderComponent) thing.getComponent("ShaderComponent")).getShader();
			
			GL20.glUseProgram(shader.getID());
			
			Services.getShader().loadVariable("projection", shader, projectionMatrix);
			
			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(2);
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((TextureComponent) thing.getComponent("TextureComponent")).getTexture().getID());
			
			Services.getShader().loadVariable("transformation", shader, TransformationMatrix.create(new Vector3(0, -2.5f, -10), new Vector3(0, rot, 0), 1));
			rot++;
			
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