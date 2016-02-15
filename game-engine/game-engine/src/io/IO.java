package io;

import dataTypes.ProcessType;
import services.window.Window;

public class IO extends ProcessType {
	
	@Override
	public void update(){
		Window.refresh();
	}
}