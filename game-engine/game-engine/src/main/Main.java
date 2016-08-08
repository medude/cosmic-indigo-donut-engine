package main;

import java.util.HashMap;

import apis.ApiHandler;
import apis.config.Config;
import apis.config.ConfigData;
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
	public static void main(String[] args) {
		Main game = new Main();
		game.run();
	}
	
	public void run() {
		try {
			
			//////////////////////////////////
			// Set up backend               //
			//////////////////////////////////
			
			ApiHandler.init(); // Init all APIs
			
			ConfigData data = Config.readFile("main.conf");
			
			Window window = WindowManager.create(data.data.get("window.title")); // Create window
			
			Console.log("APIs inited and window created succsessfully");
			
			
			//////////////////////////////////
			// Load resources               //
			//////////////////////////////////
			
			Console.log("Beginning to load resources");
			
			/// TEXTURES ///
			// Hash map for storing all textures
			HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();
			
			// Loop loads in all textures
			for(int i = 0; i < Integer.parseInt(data.data.get("texture.total")); i++) {
				textures.put(i, Loader.loadImage(data.data.get("texture." + i)));
				Console.log("Loaded texture " + (i + 1) + "/" + data.data.get("texture.total"));
			}
			
			/// MODELS ///
			// Hash map for storing all models
			HashMap<Integer, ModelData> models = new HashMap<Integer, ModelData>();
			
			// Loop loads in all models
			for(int i = 0; i < Integer.parseInt(data.data.get("model.total")); i++) {
				models.put(i, Loader.loadOBJ(data.data.get("model." + i)));
				Console.log("Loaded model " + (i + 1) + "/" + data.data.get("model.total"));
			}
			
			/// SHADERS ///
			// Hash map for storing all shaders
			HashMap<Integer, Shader> shaders = new HashMap<Integer, Shader>();
			
			// Loop loads in all shaders
			for(int i = 0; i < Integer.parseInt(data.data.get("shader.total")); i++) {
				shaders.put(i, ShaderManager.load(data.data.get("shader." + i)));
				Console.log("Loaded shader " + (i + 1) + "/" + data.data.get("shader.total"));
			}
			
			Console.log("Loaded all resources");
			
			
			//////////////////////////////////
			// Setup scene graph            //
			//////////////////////////////////
			
			// Thing thing
			Thing thing = SceneManager.makeThing(); // Create thing
			thing.addComponent(new TextureComponent(textures.get(0))); // Assign texture
			thing.addComponent(new ModelComponent(models.get(0))); // Assign model
			thing.addComponent(new ShaderComponent(shaders.get(0))); // Assign shader
			
			// Thing thing2
			Thing thing2 = SceneManager.makeThing(); // Create thing2
			thing2.addComponent(new TextureComponent(textures.get(0))); // Assign texture
			thing2.addComponent(new ModelComponent(models.get(0))); // Assign model
			thing2.addComponent(new ShaderComponent(shaders.get(0))); // Assign shader
			
			// Thing thing3
			Thing thing3 = SceneManager.makeThing(); // Create thing3
			thing3.addComponent(new TextureComponent(textures.get(0))); // Assign texture
			thing3.addComponent(new ModelComponent(models.get(0))); // Assign model
			thing3.addComponent(new ShaderComponent(shaders.get(0))); // Assign shader
			
			// Group group
			Group group = new Group(  // Create group
					new Thing[]{thing} // Assign thing as child
					);
			
			// Group group2
			Group group2 = new Group( // Create group2
					new Thing[]{thing2, thing3} // Assign thing2 and thing3 as children
					);
			
			// Area area
			Area area = new Area( // Create area
					new Group[]{group} // Assign group as child
					);
			
			// Area area2
			Area area2 = new Area( // Create area2
					new Group[]{group2} // Assign group2 as child
					);
			
			// Move area2
			area2.updateComponent("LocalTransformComponent", new TransformComponent(TransformationMatrix.create(
					new Vector3(0, -1, -5), new Vector3(90, 0, 0), 1)));
			
			// Scene scene
			Scene scene = new Scene(
					new Area[]{area, area2}, // Assign area and area2 as children
					null // Assign no area map
					);
			
			// Add the scene to the renderer
			Renderer.add(scene);
			
			Console.log("Created scene graph");
			
			
			//////////////////////////////////
			// Setup logic variables        //
			//////////////////////////////////
			
			double rot = 0; // Rotation
			boolean forward = false; // Direction
			
			Console.log("Loading complete, game ready");
			
			
			//////////////////////////////////
			// Main Game Loop               //
			//////////////////////////////////
			
			while(WindowManager.testForClose(window)) {
				
				
				//////////////////////////////////
				// Logic                        //
				//////////////////////////////////
				
				// Change direction
				if(rot == 180) {
					forward = true;
				} else if(rot == 5) {
					forward = false;
				}
				
				// Change rot
				if(forward) {
					rot--;
				} else {
					rot++;
				}
				
				// Move group
				group.updateComponent("LocalTransformComponent", new TransformComponent(TransformationMatrix.create(
						new Vector3(0, 0, -rot), new Vector3(90, rot, 0), 1)));
				
				// Apply any movements
				scene.calculateChildTransform(false);
				
				
				//////////////////////////////////
				// Render                       //
				//////////////////////////////////
				
				Renderer.render(); // Perform actual render
				WindowManager.update(window); // Show render
			}
			
		} catch(Throwable e) {
			ErrorHandle.handle(e); // Handle any errors
			
		} finally {
			Console.log("About to cleanup, game closing");
			
			ApiHandler.cleanup(); // Clean up the APIs
		}
	}
}