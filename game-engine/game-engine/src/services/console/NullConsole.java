package services.console;

public class NullConsole extends CoreConsole {
	@Override
	public void log(String message){}

	@Override
	public void log(Object message){}

	@Override
	public void error(String message){}

	@Override
	public void error(Object message){}
}
