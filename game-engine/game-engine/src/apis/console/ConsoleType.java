package apis.console;

/**
 * This interface sets the standards for the Console implementation.
 * 
 * @author medude
 */
public interface ConsoleType {
	/**
	 * This method will log to the console.
	 * 
	 * @param message
	 *            The message logged to the console
	 */
	public void log(String message);

	/**
	 * This method will log to the error console.
	 * 
	 * @param message
	 *            The message logged to the error console
	 */
	public void error(String message);

	/**
	 * This method will log to the "warn" console (standard in Java).
	 * 
	 * @param message
	 *            The message logged to the "warn" console (standard in Java)
	 */
	public void warn(String message);
}