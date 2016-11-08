package apis.renderer;

import scene.SceneNode;

public class NullRenderer implements RendererType {
	@Override
	public void render() {
	}

	@Override
	public void add(SceneNode scene) {
	}

	@Override
	public void init() {
	}
}
