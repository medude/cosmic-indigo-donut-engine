package apis.windowManager;

import apis.ApiHandler;
import dataTypes.Window;

public class WindowManager {
    private static WindowType windowObject = ApiHandler.getWindow();

    public static Window create(String title, int width, int height) {
	return windowObject.create(title, height, width);
    }

    public static Window create(int width, int height) {
	return windowObject.create("Game", height, width);
    }

    public static Window create(String title) {
	return windowObject.create(title, windowObject.getScreenHeight(), windowObject.getScreenWidth());
    }

    public static Window create() {
	return windowObject.create("Game", windowObject.getScreenHeight(), windowObject.getScreenWidth());
    }

    public static void close(Window window) {
	windowObject.close(window);
    }

    public static boolean testForClose(Window window) {
	return windowObject.testForClose(window);
    }

    public static void update(Window window) {
	windowObject.update(window);
    }

    public static int getScreenWidth() {
	return windowObject.getScreenWidth();
    }

    public static int getScreenHeight() {
	return windowObject.getScreenHeight();
    }

    public static int getWidth() {
	return windowObject.getWidth();
    }

    public static int getHeight() {
	return windowObject.getHeight();
    }
}