package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.User;

@Stateless
@Path("/users")
@LocalBean
public class UserBean {

	private List<User> users = new ArrayList<User>();
	private List<User> loggedIn = new ArrayList<User>();

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public User register(User user) {
		System.out.println(user.getUsername()+" " + user.getPassword());
		for(User u: users) {
			if(u.getUsername().equals(user.getUsername())) {
				return null;
			}
		}
		
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		
		users.add(u);
		return u;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User user) {
		System.out.println(user.getUsername()+" "+ user.getPassword());
		for(User u:users) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				loggedIn.add(user);
				return user;
			}
		}
		return null;
	}
}
