package coreFunctions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsoleOut {
	public static void log(String message){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}
	
	public static void error(String message){
		System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+" '"+message+"'");
	}
}