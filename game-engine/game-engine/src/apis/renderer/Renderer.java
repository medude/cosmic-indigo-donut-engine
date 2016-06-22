package apis.renderer;

import apis.ApiHandler;
import scene.Thing;

public class Renderer {
	private static RendererType rendererObject=ApiHandler.getRenderer();
	public static void init(){
		rendererObject.init();
	}
	
	public static void add(Thing thing){
		rendererObject.add(thing);
	}
	
	public static void render(){
		rendererObject.render();
	}
}
