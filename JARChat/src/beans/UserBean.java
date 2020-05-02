package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.UserDao;
import models.User;

@Stateless
@Path("/users")
@LocalBean
public class UserBean {

	@Context
	ServletContext context;
	
	@PostConstruct
	public void init() {
		if(context.getAttribute("userDao") == null) {
			context.setAttribute("userDao", new UserDao());
		}
	}

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public User register(User user) {
		UserDao userDao = (UserDao) context.getAttribute("userDao");
		
		for(User u: userDao.getUsers()) {
			if(u.getUsername().equals(user.getUsername())) {
				return null;
			}
		}
		
		User u = new User();
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		
		userDao.getUsers().add(u);
		System.out.println(u);
		return u;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User user) {
		UserDao userDao = (UserDao) context.getAttribute("userDao");
		
		for(User u:userDao.getUsers()) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				userDao.getLoggedIn().add(user);
				System.out.println(user.getUsername());
				context.setAttribute("userDao", userDao);
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
		UserDao userDao = (UserDao)context.getAttribute("userDao");
		return userDao.getLoggedIn();
	}
	
	@GET
	@Path("/registered")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> registered() {
		UserDao userDao = (UserDao)context.getAttribute("userDao");
		return userDao.getUsers();
	}
	
	@DELETE
	@Path("/loggedIn/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void logout(@PathParam("username") String username) {
		if(username == null)
			return;
		
		UserDao userDao = (UserDao)context.getAttribute("userDao");
		
		for(int i = 0; i < userDao.getLoggedIn().size(); i++) {
			if(userDao.getLoggedIn().get(i).getUsername().equals(username)) {
				
				userDao.getLoggedIn().remove(i);
				System.out.println("Logging out user "+ username);
			}
		}
	}
}
