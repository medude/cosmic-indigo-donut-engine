package apis.renderer;

import components.Thing;

public interface RendererType {
	public void init();
	
	public void add(Thing thing);
	
	public void render();
}
