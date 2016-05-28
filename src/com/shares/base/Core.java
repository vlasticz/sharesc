package com.shares.base;

import java.io.Serializable;
import java.util.ArrayList;

public class Core implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Family> families;
	
	
	public Core() {
		families = new ArrayList<Family>();
	}
	
	
	public boolean addFamily(String name) {
		
		if(name != null && name != "" && !isFamily(name)) {
			families.add(new Family(name, true));			
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean removeFamily(String name) {
		
		if(isFamily(name)) {
			Family family = getFamily(name);
			families.remove(family);			
			return true;
		} else {
			// family doesn't exist
			return false;			
		}
	}
	
	
	public boolean isFamily(String name) {
				
		if(getFamily(name) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public Family getFamily(String name) {
		
		for(Family family : families) {
			if(family.getName().equals(name))
				return family;
		}
		
		return null;
	}
	
	
	public ArrayList<Family> getFamilies() {
		return families;
	}

}
