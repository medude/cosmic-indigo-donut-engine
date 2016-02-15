package main;

import services.Services;
import services.window.Window;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		Services.init();
		Window.create();
		
		while(Window.isOpen()){
			Window.refresh();
		}
	}
}
