package apis.console;

public interface ConsoleType {
	public void log(String message);
	
	public void error(String message);
	
	public void warn(String message);
}