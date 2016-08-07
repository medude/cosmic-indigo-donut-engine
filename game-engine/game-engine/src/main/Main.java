package main;

import apis.ApiHandler;
import apis.console.Console;
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
			Window window = WindowManager.create("Test \"Game\"");
			
			ModelData rectangle = Loader.loadOBJ("Plane");
			Shader shader = ShaderManager.load("shader");
			Texture texture = Loader.loadImage("test");
			
			Thing thing = SceneManager.makeThing();
			thing.addComponent(new TextureComponent(texture));
			thing.addComponent(new ModelComponent(rectangle));
			thing.addComponent(new ShaderComponent(shader));
			
			Thing thing2 = SceneManager.makeThing();
			thing2.addComponent(new TextureComponent(texture));
			thing2.addComponent(new ModelComponent(rectangle));
			thing2.addComponent(new ShaderComponent(shader));
			
			Thing thing3 = SceneManager.makeThing();
			thing3.addComponent(new TextureComponent(texture));
			thing3.addComponent(new ModelComponent(rectangle));
			thing3.addComponent(new ShaderComponent(shader));
			
			Group group = new Group(new Thing[]{thing});
			Group group2 = new Group(new Thing[]{thing2, thing3});
			Area area = new Area(new Group[]{group});
			Area area2 = new Area(new Group[]{group2});
			Scene scene = new Scene(new Area[]{area, area2}, null);
			
			area2.updateComponent("LocalTransformComponent", new TransformComponent(TransformationMatrix.create(
						new Vector3(0, -1, -5), new Vector3(90, 0, 0), 1)));
			
			Renderer.add(scene);
			
			double rot = 0;
			boolean forward = false;
			
			while(WindowManager.testForClose(window)) {
				group.updateComponent("LocalTransformComponent", new TransformComponent(TransformationMatrix.create(
						new Vector3(0, 0, -rot), new Vector3(90, rot, 0), 1)));
				
				scene.calculateChildTransform(false);
				
				Console.log(((TransformComponent) thing.getComponent("LocalTransformComponent")).transform.position.toString());
				
				if(rot == 180) {
					forward = true;
				} else if(rot == 5) {
					forward = false;
				}
				
				if(forward) {
					rot--;
				} else {
					rot++;
				}
				
				
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