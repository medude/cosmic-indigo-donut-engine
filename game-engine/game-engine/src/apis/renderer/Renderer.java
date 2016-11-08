package apis.renderer;

import apis.ApiHandler;
import scene.SceneNode;

public class Renderer {
	private static RendererType rendererObject = ApiHandler.getRenderer();

	public static void init() {
		rendererObject.init();
	}

	public static void add(SceneNode scene) {
		rendererObject.add(scene);
	}

	public static void render() {
		rendererObject.render();
	}
}
