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
		Services.init();
		Window.create("Test \"Game\"");
		
		ModelData rectangle=Services.getOBJLoader().parse("stall");
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
		
		Services.cleanup();
	}
}