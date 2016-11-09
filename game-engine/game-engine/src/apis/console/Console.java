package apis.console;

import apis.ApiHandler;

/**
 * This class acts as the access point to the Console functionality. Use this to interface with consoles for debugging
 * purposes.
 * 
 * @author medude
 */
public class Console {
	// This is the actual functionality of the console
	private static ConsoleType consoleObject = ApiHandler.getConsole();

	/**
	 * This method sends <code>message</code> to the standard console quoted and with a timestamp.
	 * 
	 * @param message
	 *            The string that gets logged to the standard console
	 */
	public static void log(String message) {
		consoleObject.log(message);
	}

	/**
	 * This method sends <code>message</code> to the standard console quoted and with a timestamp by using
	 * <code>.toString()</code>. It is recommended for that reason that your objects should override
	 * <code>.toString()</code> if they use this method.
	 * 
	 * @param message
	 *            The object that gets logged to the standard console by way of <code>.toString()</code>
	 */
	public static void log(Object message) {
		consoleObject.log(message.toString());
	}

	/**
	 * This method sends <code>message</code> to the error console quoted and with a timestamp.
	 * 
	 * @param message
	 *            The string that gets logged to the error console
	 */
	public static void error(String message) {
		consoleObject.error(message);
	}

	/**
	 * This method sends <code>message</code> to the error console quoted and with a timestamp by using
	 * <code>.toString()</code>. It is recommended for that reason that your objects should override
	 * <code>.toString()</code> if they use this method.
	 * 
	 * @param message
	 *            The object that gets logged to the error console by way of <code>.toString()</code>
	 */
	public static void error(Object message) {
		consoleObject.error(message.toString());
	}

	/**
	 * This method sends <code>message</code> to the "warn" console (standard in Java) quoted and with a timestamp.
	 * 
	 * @param message
	 *            The string that gets logged to the error console
	 */
	public static void warn(String message) {
		consoleObject.warn(message);
	}

	/**
	 * This method sends <code>message</code> to the "warn" console (standard in Java) quoted and with a timestamp by
	 * using <code>.toString()</code>. It is recommended for that reason that your objects should override
	 * <code>.toString()</code> if they use this method.
	 * 
	 * @param message
	 *            The object that gets logged to the error console by way of <code>.toString()</code>
	 */
	public static void warn(Object message) {
		consoleObject.warn(message.toString());
	}
}
