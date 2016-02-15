package services.errorHandler;

public class NullErrorHandler extends CoreErrorHandler {
	@Override
	public void handle(Throwable throwable){}

	@Override
	public void handle(Throwable throwable, String message, boolean doShowError){}

	@Override
	public void handle(String message){}
}
