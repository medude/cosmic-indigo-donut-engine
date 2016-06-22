package apis.renderer;

import scene.Thing;

public class NullRenderer implements RendererType {
	@Override
	public void render(){}

	@Override
	public void add(Thing thing){}

	@Override
	public void init(){}
}
