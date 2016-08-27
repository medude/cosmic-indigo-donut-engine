package main;

import apis.ApiHandler;
import apis.config.ConfigData;
import apis.console.Console;
import apis.errorHandle.ErrorHandle;
import apis.loader.Loader;
import apis.renderer.Renderer;
import apis.windowManager.WindowManager;
import dataTypes.Window;
import scene.Scene;

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

	    ConfigData data = ApiHandler.init("conf.conf"); // Init all APIs

	    Window window = WindowManager.create(data.data.get("window.title")); // Create
										 // window
	    Console.log("APIs inited and window created succsessfully");

	    //////////////////////////////////
	    // Load resources
	    //////////////////////////////////

	    Console.log("Beginning to load resources");

	    /// TEXTURES ///
	    Loader.loadTextures(data);

	    /// MODELS ///
	    Loader.loadModels(data);

	    /// SHADERS ///
	    Loader.loadShaders(data);

	    Console.log("Loaded all resources");

	    //////////////////////////////////
	    // Setup scene graphs
	    //////////////////////////////////

	    Scene scene = Loader.loadScene("test.json");

	    // Add the scene to the renderer
	    Renderer.add(scene);

	    Console.log("Created scene graph");

	    // Loading complete!

	    Console.log("Loading complete, game ready");

	    //////////////////////////////////
	    // Main Game Loop
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
	    ErrorHandle.handle(e); // Handle any errors

	} finally {
	    Console.log("About to cleanup, game closing");

	    ApiHandler.cleanup(); // Clean up the APIs
	}
    }
}