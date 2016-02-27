package main;

import coreFunctions.Window;
import dataTypes.Shader;
import dataTypes.Texture;
import services.Services;
import thing.ModelData;
import thing.Thing;

public class Main {
	float[] vertices = {
	        -0.5f, 0.5f, 0f,    // Left top         ID: 0
	        -0.5f, -0.5f, 0f,   // Left bottom      ID: 1
	        0.5f, -0.5f, 0f,    // Right bottom     ID: 2
	        0.5f, 0.5f, 0f  // Right left       ID: 3
	};
	
	byte[] indices = {
	        // Left bottom triangle
	        0, 1, 2,
	        // Right top triangle
	        2, 3, 0
	};
	
	float[] uv={
			0,0,
			0,1,
			1,1,
			1,0
	};
	
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		Services.init();
		Window.create();
		
		ModelData rectangle=new ModelData(vertices, indices, new float[0], uv);
		Shader shader=Services.getShader().load("shader");
		Texture texture=Services.getLoader().loadImage("test");
		
		Thing thing=new Thing(shader, rectangle, texture);
		
		Services.getRenderer().add(thing);
		
		while(Window.isOpen()){
			Services.getRenderer().render();
			Window.refresh();
		}
		
		Services.cleanup();
	}
}
