package coreFunctions.window;

public abstract class CoreWindow {
	public  CoreWindow(){
		
	}
	
	public abstract void init();
	
	public abstract void refresh(long id);
	
	public abstract boolean isOpen(long id);
	
	public abstract long create(String windowName);
	
	public abstract void close(long id);

	public abstract float getScreenWidth();
	
	public abstract float getScreenHeight();
}
