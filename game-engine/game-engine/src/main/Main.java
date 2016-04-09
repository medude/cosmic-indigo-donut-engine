package main;

import components.Thing;
import components.ThingManager;
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
		Texture texture=Services.getLoader().loadImage("test");
		
		Thing thing=ThingManager.makeThing();
		thing.addComponent(new TextureComponent(ThingManager.getComponentID(), texture));
		
		Services.getRenderer().add(thing);
		
		while(Window.isOpen()){
			Services.getRenderer().render();
			Window.refresh();
		}
		
		Services.cleanup();
	}
}
