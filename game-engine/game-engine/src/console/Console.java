package console;

import dataTypes.ProcessType;
import messageBus.Message;
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
	
	@Override
	public void reciveMessage(Message message){
		console.log(message.getId());
		
		if(message.getId()==10){
			update();
		}
	}
}
