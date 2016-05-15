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
	
	
	public void addFamily(String name) {
		
		families.add(new Family(name, true));
		System.out.println("Family created");
	}
	
	
	public void removeFamily(String name) {
		if(getFamily(name) != null) {
			Family family = getFamily(name);
			families.remove(family);
			System.out.println("Family removed");
		}
		
		else
			System.out.printf("Family %s does not exist\n", name);
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
