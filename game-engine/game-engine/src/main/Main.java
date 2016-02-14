package main;

import messageBus.Message;
import messageBus.MessageBus;
import window.Window;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		Message update=new Message(10);
		MessageBus.post(new Message(11, new String[] {"Test Window"}));
		MessageBus.send();
		
		while(Window.isOpen()){
			MessageBus.post(update);
			while(MessageBus.isNotEmpty()){
				MessageBus.send();
			}
		}
		
		MessageBus.post(new Message(12));
		while(MessageBus.isNotEmpty()){
			MessageBus.send();
		}
	
	}
}
