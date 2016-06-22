package main;

import apis.ApiHandler;
import apis.errorHandle.ErrorHandle;
import apis.loader.Loader;
import apis.renderer.Renderer;
import apis.shaderManager.ShaderManager;
import apis.windowManager.WindowManager;
import components.types.ModelComponent;
import components.types.ShaderComponent;
import components.types.TextureComponent;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.Texture;
import dataTypes.Window;
import scene.Thing;
import scene.SceneManager;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		try{
			ApiHandler.init();
			Window window=WindowManager.create("Test \"Game\"");
			
			ModelData rectangle=Loader.loadOBJ("stall");
			Shader shader=ShaderManager.load("shader");
			Texture texture=Loader.loadImage("stallTexture");
			
			Thing thing=SceneManager.makeThing();
			thing.addComponent(new TextureComponent(texture));
			thing.addComponent(new ModelComponent(rectangle));
			thing.addComponent(new ShaderComponent(shader));
			
			Renderer.add(thing);
			
			while(WindowManager.testForClose(window)){
				Renderer.render();
				WindowManager.update(window);
			}
			
		}catch(Throwable e){
			ErrorHandle.handle(e);
		}finally{
			ApiHandler.cleanup();
		}
	}
}