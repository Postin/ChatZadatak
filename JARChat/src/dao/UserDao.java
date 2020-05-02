package dao;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDao {

	private List<User> users = new ArrayList<User>();
	private List<User> loggedIn = new ArrayList<User>();
	
	public UserDao() {
		super();
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(List<User> loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public User findByUsername(String username) {
		
		for(User u:users) {
			if(u.getUsername().equals(username)) {
				System.out.println("returning user: "+username);
				return u;
			}
		}
		return null;
	}
	
	
}
