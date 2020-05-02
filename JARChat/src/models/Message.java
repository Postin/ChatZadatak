package models;

import java.util.Calendar;

public class Message {
	
	private User sender;
	private User receiver;
	private String text;
	private String subject;
	private Calendar datetime;
	
	public Message() {
		super();
	}
	
	public Message(User sender, User receiver, String text, String subject, Calendar datetime) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
		this.subject = subject;
		this.datetime = datetime;
	}
	
	
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciever() {
		return receiver;
	}
	public void setReciever(User receiver) {
		this.receiver = receiver;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Calendar getDatetime() {
		return datetime;
	}
	public void setDatetime(Calendar datetime) {
		this.datetime = datetime;
	}

}
