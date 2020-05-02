package beans;


import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.MessageDao;
import dao.UserDao;
import models.Message;
import models.User;


@Stateless
@Path("/messages")
@LocalBean
public class MessageBean {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	@Resource(mappedName = "java:jboss/exported/jms/queue/mojQueue")
	private Queue queue;
	
	@Context
	ServletContext context;
	
	@PostConstruct
	public void init() {
		if(context.getAttribute("userDao") == null) {
			context.setAttribute("userDao", new UserDao());
		}
		
		if(context.getAttribute("messageDao") == null) {
			context.setAttribute("messageDao", new MessageDao());
		}
	}

	@POST
	@Path("/all")
	public void sendToAll(Message m) {
		System.out.println(m.getText());
		
		Message msg = new Message();
		msg.setSender(m.getSender());
		msg.setReciever(new User("","",null));
		msg.setText(m.getText());
		msg.setSubject("Message from "+ m.getSender().getUsername() +" to All");
		msg.setDatetime(Calendar.getInstance());
		MessageDao msgDao = (MessageDao)context.getAttribute("messageDao");
		msgDao.getMessages().add(msg);
		context.setAttribute("messageDao", msgDao);
		try {
			QueueConnection connection = (QueueConnection) connectionFactory.createConnection("guest", "guest.guest.1");
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			// create and publish a message
			TextMessage txtmsg = session.createTextMessage();
			txtmsg.setText(msg.getReciever().getUsername());
			sender.send(txtmsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("/user/{receiver}")
	public void sendMessage(Message m, @PathParam("receiver") String username) {
		UserDao userDao = (UserDao)context.getAttribute("userDao");
		User receiver = userDao.findByUsername(username);
		Message msg = new Message();
		msg.setSender(m.getSender());
		msg.setReciever(receiver);
		msg.setText(m.getText());
		msg.setSubject("Message from "+ m.getSender().getUsername() +" to"+ receiver.getUsername());
		msg.setDatetime(Calendar.getInstance());
		MessageDao msgDao = (MessageDao)context.getAttribute("messageDao");
		msgDao.getMessages().add(msg);
		context.setAttribute("messageDao", msgDao);
		try {
			QueueConnection connection = (QueueConnection) connectionFactory.createConnection("guest", "guest.guest.1");
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			// create and publish a message
			TextMessage txtmsg = session.createTextMessage();
			txtmsg.setText(msg.getReciever().getUsername());
			sender.send(txtmsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Message> showMessages(@PathParam("username") String username) {
		MessageDao msgDao = (MessageDao)context.getAttribute("messageDao");
		ArrayList<Message> messages = new ArrayList<Message>();
		for(Message m:msgDao.getMessages()) {
			if(m.getReciever().getUsername().equals(username) || m.getReciever().getUsername().equals("")) {
				messages.add(m);
			}
		}
		return messages;
	}
}
