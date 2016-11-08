package apis.renderer;

import scene.SceneNode;

public interface RendererType {
	public void init();

	public void add(SceneNode scene);

	public void render();
}
