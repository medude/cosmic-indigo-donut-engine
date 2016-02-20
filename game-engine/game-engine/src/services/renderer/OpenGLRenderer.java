package services.renderer;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import services.Services;
import thing.ModelData;

public class OpenGLRenderer extends CoreRenderer {
	private List<ModelData> datas=new ArrayList<ModelData>();
	
	@Override
	public void add(ModelData data){
		datas.add(data);
	}

	@Override
	public void render(){
		int shader=Services.getShaders().load("shader");
		
		for(ModelData data:datas){
			GL30.glBindVertexArray(data.getID());
			GL20.glEnableVertexAttribArray(0);
			
			GL20.glUseProgram(shader);
			GL20.glBindAttribLocation(shader, 0, "in_Position");
			 
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, data.getVertexCount());
			
			GL20.glUseProgram(shader);
			
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		}
	}

}
