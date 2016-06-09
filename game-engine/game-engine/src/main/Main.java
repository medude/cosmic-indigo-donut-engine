package main;

import components.Thing;
import components.ThingManager;
import components.types.ModelComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import coreFunctions.Window;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.Texture;
import services.Services;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		try{
			Services.init();
			Window.create("Test \"Game\"");
			
			ModelData rectangle=Services.getOBJLoader().parse("Plane");
			//float[] verticies={0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f};
			//short[] indicies={0, 1, 2, 3};
			//ModelData rectangle=new ModelData(verticies, indicies, new float[0], new float[0]);
			Shader shader=Services.getShader().load("shader");
			Texture texture=Services.getLoader().loadImage("stallTexture");
			
			Thing thing=ThingManager.makeThing();
			thing.addComponent(new TextureComponent(texture));
			thing.addComponent(new ModelComponent(rectangle));
			thing.addComponent(new ShaderComponent(shader));
			
			Services.getRenderer().add(thing);
			
			while(Window.isOpen()){
				Services.getRenderer().render();
				Window.refresh();
			}
		}finally{
			Services.cleanup();
		}
	}
}