package services.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import components.Thing;
import components.types.TextureComponent;
import dataTypes.ModelData;
import services.Services;
public class OpenGLRenderer extends CoreRenderer {
	private List<Thing> things=new ArrayList<Thing>();
	
	@Override
	public void add(Thing thing){
		things.add(thing);
	}

	@Override
	public void render(){
		for(Thing thing:things){
			ModelData data=thing.getData();
			
			GL20.glUseProgram(thing.getShader().getID());
			
			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(2);
			
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, data.getIndiciesID());
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((TextureComponent) thing.getComponent("TextureComponent")).getTexture().getID());
			
			// Draw the vertices
			GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_BYTE, 0);
			
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(2);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
			
			GL30.glBindVertexArray(0);
			
			GL20.glUseProgram(0);
		}
	}
}
