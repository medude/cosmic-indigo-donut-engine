package main;

import dataTypes.TextFile;
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
		TextFile file=Services.getLoader().loadFile("test.txt");
		
		Services.getConsole().log(file.getLines());
		
		while(Window.isOpen()){
			Window.refresh();
		}
	}
}
