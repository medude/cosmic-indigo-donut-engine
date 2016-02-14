package io;

import coreFunctions.CoreWindow;
import dataTypes.ProcessType;
import messageBus.Message;
import window.Window;

public class IO extends ProcessType {
	private static long window=0;
	
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
			window=Window.create("Test");
			//Window.create(message.getsData()[0]);
			break;
		case 12:
			CoreWindow.close(window);
		}
	}
	
	public static long getWindow(){
		return window;
	}
}