package com.shares.security;

import java.util.ArrayList;
import java.util.Scanner;

public class Security {
	
	private Scanner sc;
	private ArrayList<User> users;

	public Security() {
		this.sc = new Scanner(System.in);
		this.users = new ArrayList<User>();
		this.users.add(new User());
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
	

	public void addUser(String username, String password) {
		this.users.add(new User(username, password));
	}
	

	public void remUser(String username) {
		
		for(User user : users) {
			if (user.getName().equals(username)) {
				this.users.remove(user);
			}
		}
	}
	

	public User login() {
		System.out.printf("Enter username: ", new Object[0]);
		String username = this.sc.nextLine();
		System.out.printf("Enter password: ", new Object[0]);
		String password = this.sc.next();
		return this.loginUser(username, password);
	}
	

	private User loginUser(String username, String password) {
		System.out.println("name: " + username);
		System.out.println("hashed pass: " + MD5.crypt(password));
		if (this.isUser(username).booleanValue()) {
			User user = this.getUser(username);
			if (user.comparePassword(password).booleanValue()) {
				return user;
			}
		}

		return null;
	}
	

	public Boolean isUser(String name) {
		
		for(User user : users) {
			if (user.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}
}