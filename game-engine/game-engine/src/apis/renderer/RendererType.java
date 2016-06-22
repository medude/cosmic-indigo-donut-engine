package apis.renderer;

import scene.Thing;

public interface RendererType {
	public void init();
	
	public void add(Thing thing);
	
	public void render();
}
