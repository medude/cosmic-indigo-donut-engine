package services.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

import services.Services;

public class GLSLShader extends CoreShader {
	@Override
	public int load(String path){
		int vertex=0;
		int fragment=0;
		
		path="res/shaders/"+path;
		
		StringBuilder shader;
		BufferedReader reader;
		String line;
		
		try{
			//Vertex shader
			reader=new BufferedReader(new FileReader(path+".vert"));
			shader=new StringBuilder();
			
			line="";
			while ((line=reader.readLine())!=null) {
				shader.append(line).append("\n");
			}
			reader.close();
			
			vertex=GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
			GL20.glShaderSource(vertex, shader);
			GL20.glCompileShader(vertex);
			
			
			//Fragment shader
			reader=new BufferedReader(new FileReader(path+".vert"));
			shader=new StringBuilder();
			
			line="";
			while ((line=reader.readLine())!=null) {
				shader.append(line).append("\n");
			}
			reader.close();
			
			vertex=GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
			GL20.glShaderSource(vertex, shader);
			GL20.glCompileShader(vertex);
			
			int program=GL20.glCreateProgram();
			GL20.glAttachShader(program, vertex);
			GL20.glAttachShader(program, fragment);
			
			return program;
			
		}catch(IOException e){
			Services.getErrorHandler().handle(e);
		}
		
		return 0;
	}
}
