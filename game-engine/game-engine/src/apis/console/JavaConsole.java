package apis.console;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaConsole implements ConsoleType {
	@Override
	public void log(String message) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}

	@Override
	public void error(String message) {
		System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}

	@Override
	public void warn(String message) {
		System.out.println("Warning: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}
}
