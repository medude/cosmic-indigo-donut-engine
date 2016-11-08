package apis.errorHandler;

public interface ErrorHandlerType {
	public void handle(Throwable throwable, String message, boolean showError);
}