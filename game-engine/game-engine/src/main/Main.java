package main;

import apis.ApiHandler;
import apis.console.Console;
import apis.errorHandler.ErrorHandler;
import apis.loader.Loader;
import apis.renderer.Renderer;
import apis.windowManager.WindowManager;
import dataTypes.Window;
import scene.SceneNode;

/**
 * This class contains the only application entry point, and the only other method, run, contains the entirety of the
 * program.
 * 
 * @author medude
 */
public class Main {
	/**
	 * This method is the game engine entry point and executes the {@link Main#run() run()} method.
	 * 
	 * @param args
	 *            The arguments passed to the JVM
	 */
	public static void main(String[] args) {
		Main game = new Main();
		game.run();
	}

	/**
	 * This method is called by {@link Main#main(String[]) main()} and acts as the init and game loop threads.
	 */
	private void run() {
		try {
			//////////////////////////////////
			// Set up backend               //
			//////////////////////////////////

			ApiHandler.init("config/config.json"); // Init all APIs

			Window window = WindowManager.create((String) Loader.getconfigData().data.get("windowTitle").getData()); // Create
			// window
			Console.log("APIs inited and window created succsessfully");

			//////////////////////////////////
			// Load resources               //
			//////////////////////////////////

			Console.log("Beginning to load resources");

			/// TEXTURES ///
			Loader.loadTextures();

			/// MODELS ///
			Loader.loadModels();

			/// SHADERS ///
			Loader.loadShaders();

			Console.log("Loaded all resources");

			//////////////////////////////////
			// Setup scene graphs           //
			//////////////////////////////////

			SceneNode scene = Loader.loadScene("scene.json");

			// Add the scene to the renderer
			Renderer.add(scene);

			Console.log("Created scene graph");

			// Loading complete!

			Console.log("Loading complete, game ready");

			//////////////////////////////////
			// Main Game Loop               //
			//////////////////////////////////

			while (WindowManager.testForClose(window)) {

				//////////////////////////////////
				// Logic
				//////////////////////////////////

				// Apply any movements
				scene.calculateChildTransform(false);

				//////////////////////////////////
				// Render
				//////////////////////////////////

				Renderer.render(); // Perform actual render
				WindowManager.update(window); // Show render
			}

		} catch (Throwable e) {
			ErrorHandler.handle(e); // Handle any errors

		} finally {
			Console.log("About to cleanup, game closing");

			ApiHandler.cleanup(); // Clean up the APIs
		}
	}
}