package dataTypes;

import messageBus.Message;

public abstract class ProcessType {
	public abstract void update();
	public abstract void reciveMessage(Message message);
}
