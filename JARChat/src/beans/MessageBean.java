package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("/messages")
@LocalBean
public class MessageBean {

	@POST
	@Path("/all")
	public void sendToAll() {
		
	}
}
