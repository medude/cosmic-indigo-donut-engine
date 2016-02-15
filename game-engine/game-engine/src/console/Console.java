package console;

import dataTypes.ProcessType;
import services.Services;
import services.console.CoreConsole;

public class Console extends ProcessType {
	private static CoreConsole console;
	
	public Console(){
		console=Services.getConsole();
	}
	
	@Override
	public void update(){
		
	}
}
