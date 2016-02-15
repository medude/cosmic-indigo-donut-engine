package services.errorHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import services.Services;
import services.console.CoreConsole;

public class JavaErrorHandler extends CoreErrorHandler {
	CoreConsole console=Services.getConsole();
	
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
			Services.getConsole().error(message+"\n"+error);
		}else{
			console.error(message);
		}
		
		System.exit(-1);
	}

	@Override
	public void handle(String message){
		console.error(message);
		
		System.exit(-1);
	}
}
