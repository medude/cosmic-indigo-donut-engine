package apis.windowManager;

import dataTypes.Window;

public interface WindowType {
	public Window create(String title, int width, int height);
	
	public void close(Window window);
	
	public boolean testForClose(Window window);
	
	public void update(Window window);
	
	public void init();

	public int getScreenWidth();
	
	public int getScreenHeight();
	
	public int getWidth();
	
	public int getHeight();
}
