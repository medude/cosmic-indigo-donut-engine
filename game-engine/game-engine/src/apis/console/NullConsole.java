package apis.console;

/**
 * This class implements the console functionality but does nothing (null device).
 * 
 * @author medude
 *
 */
public class NullConsole implements ConsoleType {
	/**
	 * This method does nothing (it's the NullConsole).
	 */
	@Override
	public void log(String message) {
	}

	/**
	 * This method does nothing (it's the NullConsole).
	 */
	@Override
	public void error(String message) {
	}

	/**
	 * This method does nothing (it's the NullConsole).
	 */
	@Override
	public void warn(String message) {
	}
}
