package services.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import services.Services;

public class GLSLShader extends CoreShader {
	private List<Integer> shaders=new ArrayList<Integer>();
	private List<Integer> programs=new ArrayList<Integer>();
	
	@Override
	public int load(String path){
		int vertex=0;
		int fragment=0;
		
		path="res/shaders/"+path;
		
		//Vertex shader
		vertex=createShader(path+".vert", GL20.GL_VERTEX_SHADER);
		
		//Fragment shader
		fragment=createShader(path+".frag", GL20.GL_FRAGMENT_SHADER);
		
		//Program
		int program=GL20.glCreateProgram();
		
		GL20.glAttachShader(program, vertex);
		GL20.glAttachShader(program, fragment);
		
		GL20.glBindAttribLocation(program, 0, "in_Position");
		
		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);
		
		programs.add(program);
		
		return program;
	}
	
	@Override
	public void cleanup(){
		GL20.glUseProgram(0);
		
		for(int i=0; i<programs.size(); i++){
			GL20.glDetachShader(programs.get(i), shaders.get(2*i));
			GL20.glDetachShader(programs.get(i), shaders.get(2*i+1));
			 
			GL20.glDeleteShader(shaders.get(2*i));
			GL20.glDeleteShader(shaders.get(2*i+1));
			GL20.glDeleteProgram(programs.get(i));
		}
	}
	
	private int createShader(String path, int type){
		StringBuilder shaderSource=new StringBuilder();
		
		try {
			BufferedReader reader=new BufferedReader(new FileReader(path));
			String line=new String();
			
			while ((line=reader.readLine())!=null) {
				shaderSource.append(line).append("\n");
			}
			
			reader.close();
			
		} catch (IOException e) {
			Services.getErrorHandler().handle(e);
		}
		
		int shader=GL20.glCreateShader(type);
		GL20.glShaderSource(shader, shaderSource);
		GL20.glCompileShader(shader);
		
		if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE){
			Services.getErrorHandler().handle(GL20.glGetShaderInfoLog(shader));
		}
		
		shaders.add(shader);
		
		return shader;
	}
}
