package services.errorHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import apis.console.Console;

public class JavaErrorHandler extends CoreErrorHandler {
	@Override
	public void handle(Throwable throwable){
		handle(throwable, "Error!", true);
	}

	@Override
	public void handle(Throwable throwable, String message, boolean doShowError){
		StringWriter errorS=new StringWriter();
		PrintWriter errorThing=new PrintWriter(errorS);
		String error;
		
		throwable.printStackTrace(errorThing);
		error=errorS.toString();
		
		if(doShowError){
			Console.error(message+"\n"+error);
		}else{
			Console.error(message);
		}
		
		System.exit(-1);
	}

	@Override
	public void handle(String message){
		Console.error(message);
		
		System.exit(-1);
	}
}
