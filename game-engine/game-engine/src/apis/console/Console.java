package apis.console;

import apis.ApiHandler;

public class Console {
	private static ConsoleType consoleObject = ApiHandler.getConsole();

	public static void log(String message) {
		consoleObject.log(message);
	}

	public static void log(Object message) {
		consoleObject.log(message.toString());
	}

	public static void error(String message) {
		consoleObject.error(message);
	}

	public static void error(Object message) {
		consoleObject.error(message.toString());
	}

	public static void warn(String message) {
		consoleObject.warn(message);
	}

	public static void warn(Object message) {
		consoleObject.warn(message.toString());
	}
}
