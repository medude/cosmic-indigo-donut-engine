package services.window;

import services.Services;

public class Window {
	private static CoreWindow window;
	private static long id;
	
	public static void create(){
		window=Services.getWindow();
		window.init();
		id=window.create("Game");
	}
	
	public static void create(String title, CoreWindow windowType){
		window=windowType;
		window.init();
		id=window.create(title);
	}
	
	public static void refresh(){
		window.refresh(id);
	}
	
	public static boolean isOpen(){
		return window.isOpen(id);
	}
	
	public static void close(){
		window.close(id);
	}
}
