package com.shares.security;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Boolean blocked = false;
	private ArrayList<Boolean> permissions = new ArrayList<Boolean>();
	

	public User(String name, String password) {
		if (name != null || name != "root") {
			this.username = name;
		}

		if (password != "" && password != null) {
			this.password = MD5.crypt(password);
		}

		this.initPermissions();
	}
	

	public User() {
		this.username = "root";
		this.password = MD5.crypt("admin");
		this.initPermissions();
	}
	

	private void initPermissions() {
		Boolean newFamily = Boolean.valueOf(false);
		Boolean removeFamily = Boolean.valueOf(false);
		this.permissions.add(newFamily);
		this.permissions.add(removeFamily);
	}
	

	public String getName() {
		
		return this.username;
	}
	

	public String getPass() {
		
		return this.password;
	}
	
	
	public Boolean isBlocked() {
		
		return this.blocked;
	}
	

	public Boolean comparePassword(String password) {
		
		return MD5.crypt(password).equals(this.password) ? Boolean.valueOf(true) : Boolean.valueOf(false);
	}
}