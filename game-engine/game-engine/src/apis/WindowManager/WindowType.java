package apis.WindowManager;

import dataTypes.Window;

public abstract class WindowType {
	public abstract Window create(String title, int width, int height);
	
	public abstract void close(Window window);
	
	public abstract boolean testForClose(Window window);
	
	public abstract void update(Window window);
	
	public abstract void init();

	public abstract int getScreenWidth();
	
	public abstract int getScreenHeight();
}
