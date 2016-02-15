package coreFunctions;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorHandler {
	public static void handle(Throwable throwable){
		StringWriter errorS=new StringWriter();
		PrintWriter errorThing = new PrintWriter(errorS);
		throwable.printStackTrace(errorThing);
		String error=errorS.toString();
		
		CoreConsole.error("Error!\n"+error+"\n\nSorry for the inconvinience!");
		
		System.exit(-1);
	}
	
	public static void handle(Throwable throwable, String message, boolean doShowError){
		StringWriter errorS=new StringWriter();
		PrintWriter errorThing=new PrintWriter(errorS);
		throwable.printStackTrace(errorThing);
		String error=errorS.toString();
		
		if(doShowError){
			CoreConsole.error(message+error);
		}else{
			CoreConsole.error(message);
		}
		
		System.exit(-1);
	}
	
	public static void handle(String message){
		CoreConsole.error(message);
		
		System.exit(-1);
	}
}