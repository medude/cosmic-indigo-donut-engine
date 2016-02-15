package console;

import coreFunctions.CoreConsole;
import dataTypes.ProcessType;
import messageBus.Message;

public class Console extends ProcessType {
	@Override
	public void update(){
		
	}
	
	@Override
	public void reciveMessage(Message message){
		CoreConsole.log(message.getId());
		
		if(message.getId()==10){
			update();
		}
	}
}
