package main;

import coreFunctions.window.GLFWWindow;
import window.Window;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		Window.create(new GLFWWindow());
		
		while(Window.isOpen()){
			Window.refresh();
		}
	}
}
