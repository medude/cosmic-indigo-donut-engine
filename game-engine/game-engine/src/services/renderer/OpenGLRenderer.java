package services.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import thing.ModelData;
import thing.Thing;

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
			
			GL20.glUseProgram(thing.getShader());
			
			GL30.glBindVertexArray(data.getVAOID());
			GL20.glEnableVertexAttribArray(0);
			
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, data.getIndiciesID());
			 
			// Draw the vertices
			GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_BYTE, 0);
			
			GL20.glDisableVertexAttribArray(0);
			
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
			
			GL30.glBindVertexArray(0);
			
			GL20.glUseProgram(0);
		}
	}
}
