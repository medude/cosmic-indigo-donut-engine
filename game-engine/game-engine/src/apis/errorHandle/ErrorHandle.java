package apis.errorHandle;

import apis.ApiHandler;

public class ErrorHandle {
    private static ErrorHandleType errorObject = ApiHandler.getErrorHandler();

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
