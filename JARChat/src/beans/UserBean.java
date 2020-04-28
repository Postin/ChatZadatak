package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
		System.out.println(user.getUsername() +" "+ user.getPassword());
		for(User u: users) {
			if(u.getUsername().equals(user.getUsername())) {
				return null;
			}
		}
		
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		
		users.add(u);
		System.out.println(u);
		return u;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User user) {
		System.out.println(user.getUsername()+" "+ user.getPassword());
		for(User u:users) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				loggedIn.add(user);
				return u;
			}
		}
		return null;
	}
	
	@GET
	@Path("/loggedIn")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> loggedIn() {
		System.out.println("loggedIn");
		return loggedIn;
	}
	
	@GET
	@Path("/registered")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> registered() {
		return users;
	}
	
	@DELETE
	@Path("/loggedIn/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void logout(@PathParam("username") String username) {
		if(username == null)
			return;
		
		for(int i = 0; i < loggedIn.size(); i++) {
			if(loggedIn.get(i).getUsername().equals(username)) {
				
				loggedIn.remove(i);
				System.out.println("Logging out user "+ username);
			}
		}
	}
}
