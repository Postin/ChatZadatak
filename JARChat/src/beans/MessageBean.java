package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("/messages")
@LocalBean
public class MessageBean {

}
