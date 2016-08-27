package apis.errorHandle;

import java.io.PrintWriter;
import java.io.StringWriter;

import apis.console.Console;

public class JavaErrorHandler implements ErrorHandleType {
    @Override
    public void handle(Throwable throwable, String message, boolean doShowError) {
	StringWriter errorS = new StringWriter();
	PrintWriter errorThing = new PrintWriter(errorS);
	String error;

	throwable.printStackTrace(errorThing);
	error = errorS.toString();

	if (message == null) {
	    message = throwable.getMessage();
	}

	if (doShowError) {
	    Console.error(message + "\n" + error);
	} else {
	    Console.error(message);
	}

	System.exit(0);
    }
}
