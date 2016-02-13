package console;

import dataTypes.ProcessType;
import messageBus.Message;

public class Console extends ProcessType {
	@Override
	public void update(){
		
	}
	
	@Override
	public void reciveMessage(Message message){
		System.out.println(message.getId());
		
		if(message.getId()==10){
			update();
		}
	}
}
