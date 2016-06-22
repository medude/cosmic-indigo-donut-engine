package apis.renderer;

import apis.ApiHandler;
import scene.Scene;

public class Renderer {
	private static RendererType rendererObject=ApiHandler.getRenderer();
	public static void init(){
		rendererObject.init();
	}
	
	public static void add(Scene scene){
		rendererObject.add(scene);
	}
	
	public static void render(){
		rendererObject.render();
	}
}
