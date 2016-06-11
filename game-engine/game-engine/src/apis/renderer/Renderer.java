package apis.renderer;

import apis.ApiHandler;
import components.Thing;

public class Renderer {
	private static RendererType rendererObject=ApiHandler.getRenderer();
	public static void init(){
	}
	
	public static void add(Thing thing){
		rendererObject.add(thing);
	}
	
	public static void render(){
		rendererObject.render();
	}
}
