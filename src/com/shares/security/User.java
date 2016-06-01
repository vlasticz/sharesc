package com.shares.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1412899607514587138L;
	private String username;
	private String password;
	private Boolean blocked = false;
	private HashMap<String, Boolean> permissions = PermissionsFactory.getPermissions();
	private ArrayList<String> homeFamilies = new ArrayList<String>();
	
	
	public User(String name, String password, ArrayList<String> homeFamilies) {
		
		this.username = name;
		this.password = MD5.crypt(password);
		this.homeFamilies = homeFamilies;
	}
	

	public User(String name, String password) {
		
		this.username = name;
		this.password = MD5.crypt(password);
	}
	

	public User() {
		
		this.username = "root";
		this.password = MD5.crypt("admin");
	}
	
	
	public Boolean getPermissionValue(String permission) {
		
		return permissions.get(permission);
	}
	
	
	public boolean setPermissionValue(String permission, Boolean value) {
		
		if(PermissionsFactory.isPermission(permission)) {
			permissions.put(permission, value);
			return true;
		} else {
			return false;
		}
	}
	
	
	public HashMap<String, Boolean> getPermissions() {
		
		return permissions;
	}
	
	
	/**
	 * DEBUG method
	 */	
	public void printPermissions() {
		
		Set<Map.Entry<String, Boolean>> set = permissions.entrySet();
		
		for(Map.Entry<String, Boolean> permission : set) {
			System.out.println(permission.getKey() + " = " + permission.getValue());
		}
	}
	

	public String getName() {
		
		return this.username;
	}
	

	public String getPass() {
		
		return this.password;
	}
	
	
	public ArrayList<String> getHomeFamilies() {
		
		return this.homeFamilies; 
	}
	
	
	public boolean isHomeFamily(String name) {
		
		for(String s : homeFamilies) {
			if(s.equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean addHomeFamily(String name) {
		
		if(!isHomeFamily(name)) {
			homeFamilies.add(name);
			return true;
		} else {
			return false;
		}
	}
	
	
	public Boolean isBlocked() {
		
		return this.blocked;
	}
	

	public boolean comparePassword(String password) {
		
		return MD5.crypt(password).equals(this.password) ? true : false;
	}
}