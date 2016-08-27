package apis.renderer;

import scene.Scene;

public interface RendererType {
    public void init();

    public void add(Scene scene);

    public void render();
}
