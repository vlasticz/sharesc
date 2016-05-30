package com.shares.security;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Security {
	
	private Console console;
	private Scanner sc;
	private boolean useScanner = false;
	private ArrayList<User> users;

	public Security() {
		
		// Console works only if the app is run outside of the IDE.
		
		this.console = System.console();
		if (console == null) {	        
	        this.sc = new Scanner(System.in);
	        useScanner = true;
	    }
		this.users = new ArrayList<User>();
		this.users.add(UserFactory.getDefaultUser());
	}
	
	
	private boolean isUser(String name) {
				
		if(getUser(name) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean isUserPermitted(String name, String permission) {
		
		if(isUser(name) && PermissionsFactory.isPermission(permission)) {
			
			if(getUser(name).getPermissionValue(permission)) {								// Returns true or false but it's Boolean not boolean.
				return true;
				
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	

	public User getUser(String name) {
		
		for(User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}

		return null;
	}
	

	public ArrayList<User> getUsers() {
		return this.users;
	}
	

	public Boolean setUsers(ArrayList<User> users) {
		
		if (users.isEmpty()) {
			return false;
		} else {
			this.users = users;
			return true;
		}
	}
	

	public boolean addUser(String username, String password) {
		
		if(!isUser(username)) {
			this.users.add(UserFactory.getUser(username, password));
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public boolean addUser(String username, String password, ArrayList<String> homeFamilies) {
		
		if(!isUser(username)) {
			this.users.add(UserFactory.getUser(username, password, homeFamilies));
			return true;
		} else {
			return false;
		}
		
	}
	

	public boolean remUser(String username) {
				
		if(isUser(username)) {
			User user = getUser(username);
			this.users.remove(user);
			return true;
		} else {
			return false;
		}
	}
	

	public User login() {
		
		if(useScanner) {
			System.out.printf("Enter username: ");
			String username = this.sc.nextLine();
			System.out.printf("Enter password: ");
			String password = this.sc.nextLine();
			return this.loginUser(username, password);
			
		} else {
			System.out.printf("Enter username: ", new Object[0]);
			String username = this.console.readLine();
			System.out.printf("Enter password: ", new Object[0]);
			String password = String.valueOf(this.console.readPassword());
			return this.loginUser(username, password);
		}
	}
	

	private User loginUser(String username, String password) {
		
		if (this.isUser(username)) {
			User user = this.getUser(username);
			if (user.comparePassword(password)) {
				return user;
			}
		}

		return null;
	}
}