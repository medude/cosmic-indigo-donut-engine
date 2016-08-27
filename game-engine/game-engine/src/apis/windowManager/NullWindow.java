package apis.windowManager;

import dataTypes.Window;

public class NullWindow implements WindowType {

    @Override
    public int getScreenHeight() {
	return 0;
    }

    @Override
    public Window create(String title, int width, int height) {
	return null;
    }

    @Override
    public void close(Window window) {
    }

    @Override
    public boolean testForClose(Window window) {
	return true;
    }

    @Override
    public void update(Window window) {
    }

    @Override
    public int getWidth() {
	return 0;
    }

    @Override
    public int getHeight() {
	return 0;
    }

    @Override
    public void init() {
    }

    @Override
    public int getScreenWidth() {
	// TODO Auto-generated method stub
	return 0;
    }
}