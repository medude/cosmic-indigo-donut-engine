package messageBus;

import java.util.ArrayList;
import java.util.List;

import io.IO;

public class MessageBus {
	private static List<Message> messages=new ArrayList<Message>();
	
	private static IO io=new IO();
	
	public static void post(Message message){
		messages.add(message);
	}
	
	public static void send(){
		Message message=messages.get(0);
		io.reciveMessage(message);
		messages.remove(0);
	}
	
	public static boolean isNotEmpty(){
		return !messages.isEmpty();
	}
}