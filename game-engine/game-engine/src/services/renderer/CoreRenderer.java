package services.renderer;

import components.Thing;

public abstract class CoreRenderer {
	public abstract void init();
	public abstract void add(Thing thing);
	public abstract void render();
}
