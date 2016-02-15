package services.console;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JavaConsole extends CoreConsole {
	@Override
	public void log(String message) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}

	@Override
	public void log(Object message) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+String.valueOf(message)+"'");
	}

	@Override
	public void error(String message) {
		System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}

	@Override
	public void error(Object message) {
		System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+String.valueOf(message)+"'");
	}
}
