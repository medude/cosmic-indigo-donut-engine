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
import components.types.TransformComponent;
import dataTypes.ModelData;
import dataTypes.Shader;
import dataTypes.Texture;
import dataTypes.Window;
import math.TransformationMatrix;
import math.Vector3;
import scene.Thing;
import scene.Area;
import scene.Group;
import scene.Scene;
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
			thing.addComponent(new TransformComponent(TransformationMatrix.create(new Vector3(0, -2.5f, -20), new Vector3(0, 0, 0), 1)));
			
			Group group=new Group(new Vector3(0, 0, 0), null, new Thing[]{thing});
			Area area=new Area(new Group[]{group});
			Scene scene=new Scene(new Area[]{area}, null);
			
			Renderer.add(scene);
			
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