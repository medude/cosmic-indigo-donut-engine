package main;

import apis.ApiHandler;
import apis.WindowManager.WindowManager;
import apis.loader.Loader;
import components.Thing;
import components.ThingManager;
import components.types.ModelComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.Texture;
import dataTypes.Window;
import services.Services;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		try{
			Services.init();
			ApiHandler.init();
			Window window=WindowManager.create("Test \"Game\"");
			
			ModelData rectangle=Services.getOBJLoader().parse("stall");
			Shader shader=Services.getShader().load("shader");
			Texture texture=Loader.loadImage("stallTexture");
			
			Thing thing=ThingManager.makeThing();
			thing.addComponent(new TextureComponent(texture));
			thing.addComponent(new ModelComponent(rectangle));
			thing.addComponent(new ShaderComponent(shader));
			
			Services.getRenderer().add(thing);
			
			while(WindowManager.testForClose(window)){
				Services.getRenderer().render();
				WindowManager.update(window);
			}
			
		}catch(Throwable e){
			Services.getErrorHandler().handle(e);
		}finally{
			Services.cleanup();
			ApiHandler.cleanup();
		}
	}
}