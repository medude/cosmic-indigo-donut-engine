package main;

import services.Services;
import services.window.Window;
import thing.ModelData;

public class Main {
	public static void main(String[] args){
		Main game=new Main();
		game.run();
	}
	
	public void run(){
		Services.init();
		Window.create();
		
		ModelData model=Services.getOBJLoader().parse("bunny.obj");
		Services.getRenderer().add(model);
		
		while(Window.isOpen()){
			Services.getRenderer().render();
			Window.refresh();
		}
		
		Services.cleanup();
	}
}
