package apis.WindowManager;

import dataTypes.Window;

public class NullWindow extends WindowType {
	@Override
	public void init(){}

	@Override
	public int getScreenWidth(){return 0;}

	@Override
	public int getScreenHeight(){return 0;}

	@Override
	public Window create(String title, int width, int height){return null;}

	@Override
	public void close(Window window){}
	@Override
	public boolean testForClose(Window window){return true;}

	@Override
	public void update(Window window){}
}