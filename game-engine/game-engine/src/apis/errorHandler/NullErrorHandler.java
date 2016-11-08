package apis.errorHandler;

public class NullErrorHandler implements ErrorHandlerType {
	@Override
	public void handle(Throwable throwable, String message, boolean doShowError) {
	}
}
