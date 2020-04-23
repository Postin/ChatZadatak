package ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;



@Singleton
@ServerEndpoint("/ws")
@LocalBean
public class WSEndPoint {
	static List<Session> sessions = new ArrayList<Session>();
	
	@OnOpen
	public void onOpen(Session session) {
		if(!sessions.contains(session)) {
			sessions.add(session);
		}
	}
	
	@OnMessage
	public void echoTextMessage(Session session, String msg, boolean last) {
		try {
			if(session.isOpen()) {
				for(Session s: sessions) {
					if(s.getId().equals(session.getId())) {
						s.getBasicRemote().sendText(msg,last);
					}
				}
			}
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void close(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		sessions.remove(session);
		t.printStackTrace();
	}
	
}
