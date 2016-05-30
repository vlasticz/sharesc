package com.shares.security;

import java.util.HashMap;

public class PermissionsFactory {
		
	
	public static HashMap<String, Boolean> getPermissions() {
		
		HashMap<String, Boolean> permissions = new HashMap<String, Boolean>();
		
		permissions.put("users_admin", false);
		permissions.put("new_family", false);
		permissions.put("new_perk", true);
		
		return permissions;
	}
	
	
	public static boolean isPermission(String permission) {
		
		if(getPermissions().get(permission) != null) {
			return true;
		} else {
			return false;
		}
	}
}
