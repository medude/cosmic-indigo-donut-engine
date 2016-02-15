package io;

import dataTypes.ProcessType;
import messageBus.Message;
import services.window.Window;

public class IO extends ProcessType {
	
	@Override
	public void update(){
		Window.refresh();
	}
	
	@Override
	public void reciveMessage(Message message){
		int id=message.getId();
		switch(id){
		default:
			break;
		case -1:
			System.exit(-1);
			break;
		case 0:
			System.exit(0);
			break;
		case 10:
			update();
			break;
		case 11:
			//Window.create("Test");
			break;
		case 12:
			Window.close();
		}
	}
}