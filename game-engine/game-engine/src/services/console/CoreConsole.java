package services.console;

public abstract class CoreConsole {
	public abstract void log(String message);

	public abstract void log(Object message);
	
	public abstract void error(String message);
	
	public abstract void error(Object message);
	
	public abstract void warn(String message);
	
	public abstract void warn(Object message);
}