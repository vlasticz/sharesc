package com.shares.base;

import java.util.ArrayList;

public class User {
	
	private String name, password;
	
	private ArrayList<Boolean> permissions = new ArrayList<>();
	
	
	public User(String name, String password) {
		if(name != "" && name != null)
			this.name = name;
		
		if(password != "" && password != null)
			this.password = password;
		
		initPermissions();
	}
	
	
	private void initPermissions() {
		Boolean newFamily = false,
				removeFamily = false;
		
		permissions.add(newFamily);
		permissions.add(removeFamily);
	}
	
	
	public String grantAll() {
		
		for(Boolean perm : permissions) {
			perm = true;
		}
		return "Permissions granted";
	}

}
