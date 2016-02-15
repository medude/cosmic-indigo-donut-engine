package messageBus;

import java.util.ArrayList;
import java.util.List;

import console.Console;
import coreFunctions.CoreConsole;
import io.IO;

public class MessageBus {
	private static List<Message> messages=new ArrayList<Message>();
	
	private static Console console=new Console();
	private static IO io=new IO();
	
	public static void post(Message message){
		messages.add(message);
	}
	
	public static void send(){
		Message message=messages.get(0);
		io.reciveMessage(message);
		console.reciveMessage(message);
		messages.remove(0);
		for(Message message1:messages){
			CoreConsole.log(String.valueOf(message1.getId()));
		}
	}
	
	public static boolean isNotEmpty(){
		return !messages.isEmpty();
	}
}