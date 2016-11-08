package apis.errorHandler;

import apis.ApiHandler;

/**
 * This class acts as the access point to the ErrorType functionality. Use this to handle basic errors.
 * 
 * @author medude
 */
public class ErrorHandler {
	// This is the actual functionality of the ErrorHandler
	private static ErrorHandlerType errorObject = ApiHandler.getErrorHandler();

	public static void handle(Throwable throwable) {
		errorObject.handle(throwable, null, true);
	}

	public static void handle(Throwable throwable, String message, boolean showError) {
		errorObject.handle(throwable, message, showError);
	}

	public static void handle(String message) {
		errorObject.handle(new Throwable(), message, false);
	}
}
