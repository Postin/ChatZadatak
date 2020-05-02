package dao;

import java.util.ArrayList;
import java.util.List;

import models.Message;

public class MessageDao {


	private List<Message> messages = new ArrayList<Message>();
	
	public MessageDao() {
		super();
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
