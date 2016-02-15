package services;

import services.console.CoreConsole;
import services.console.JavaConsole;
import services.console.NullConsole;
import services.errorHandler.CoreErrorHandler;
import services.errorHandler.JavaErrorHandler;
import services.errorHandler.NullErrorHandler;
import services.loader.CoreLoader;
import services.loader.JavaLoader;
import services.loader.NullLoader;
import services.window.CoreWindow;
import services.window.GLFWWindow;
import services.window.NullWindow;

public class Services {
	public static void init(){
		errorHandlers[0]=new JavaErrorHandler();
		errorHandlers[1]=new NullErrorHandler();
		
		consoles[0]=new JavaConsole();
		consoles[1]=new NullConsole();
		
		windows[0]=new GLFWWindow();
		windows[1]=new NullWindow();
		
		loaders[0]=new JavaLoader();
		loaders[1]=new NullLoader();
	}
	
	//////////////////////////////////
	//Error Handlers                //
	//////////////////////////////////
	private static CoreErrorHandler[] errorHandlers=new CoreErrorHandler[2];
	
	public static CoreErrorHandler getErrorHandler(){
		return errorHandlers[0];
	}
	
	//////////////////////////////////
	//Consoles                      //
	//////////////////////////////////
	private static CoreConsole[] consoles=new CoreConsole[2];
	
	public static CoreConsole getConsole(){
		return consoles[0];
	}
	
	//////////////////////////////////
	//Windows                       //
	//////////////////////////////////
	private static CoreWindow[] windows=new CoreWindow[2];
	
	public static CoreWindow getWindow(){
		return windows[0];
	}
	
	//////////////////////////////////
	//Loaders                       //
	//////////////////////////////////
	private static CoreLoader[] loaders=new CoreLoader[2];
	
	public static CoreLoader getLoader(){
	return loaders[0];
	}
}
