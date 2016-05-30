package com.shares.security;

import java.util.ArrayList;

public class UserFactory {
	
	
	private static boolean isDefaultUserSet = false;
	
	
	public static User getUser(String username, String password) {
		
		if(username != null && username != "" && username != "root" && password != "" && password != null) {
			return new User(username, password);
						
		} else {
			return null;
		}
	}
	
	
	public static User getUser(String username, String password, ArrayList<String> homeFamilies) {
		
		if(username != null && username != "" && username != "root" && password != "" && password != null) {
			return new User(username, password, homeFamilies);
						
		} else {
			return null;
		}
	}
	
	
	public static User getDefaultUser() {
		
		if(!isDefaultUserSet) {
			
			isDefaultUserSet = true;
			
			User user = new User();
			user.setPermissionValue("users_admin", true);
			
			return user;
			
		} else {
			return null;
		}
		
	}
}
