package window;

import coreFunctions.CoreWindow;

public class Window {
	private static long id;
	
	public static long create(){
		id=CoreWindow.create();
		return id;
	}
	
	public static long create(String title){
		id=CoreWindow.create(title);
		return id;
	}
	
	public static void refresh(){
		CoreWindow.refresh(id);
	}
	
	public static boolean isOpen(){
		return CoreWindow.isOpen(id);
	}
}
