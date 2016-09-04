package apis.console;

public class NullConsole implements ConsoleType {
	@Override
	public void log(String message) {
	}

	@Override
	public void error(String message) {
	}

	@Override
	public void warn(String message) {
	}
}
