package services.errorHandler;

public abstract class CoreErrorHandler {
	public abstract void handle(Throwable throwable);
	
	public abstract void handle(Throwable throwable, String message, boolean doShowError);
	
	public abstract void handle(String message);
}