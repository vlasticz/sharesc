package com.shares.base;

import java.io.Serializable;
import java.util.ArrayList;

public class Family implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FILE_SUFFIX = ".fsf";		// family save file :) 
	private String name, fileName;//, caption;
	private ArrayList<Perk> perkCollection;
	
	
	public Family(String name, boolean bool) {
		if(bool) {
			
			this.name = name;
			//this.caption = name;
			
			perkCollection = new ArrayList<Perk>();
		}		
	}
	
	
	public Family(String name, String caption) {
		
		this.name = name;
		//this.caption = caption;
		
		perkCollection = new ArrayList<Perk>();
	}
	
	
	public void addPerk(String name) {
		
		if(name != null | name != "") {
			perkCollection.add(new Perk(name));
			System.out.println("Perk added");
		}
	}
	
	
	public void removePerk(String name) {
		
		if(getPerk(name) != null) {			
			perkCollection.remove(getPerk(name));
			System.out.println("Perk removed");
		}
		
		else
			System.out.printf("Perk %s does not exist\n", name);
	}
	
	
	public Perk getPerk(String perkName) {
		
		for(Perk p: perkCollection) {
			if(p.getName().equals(perkName))
				return p;
		}
		
		return null;
	}
	
	
	public ArrayList<Perk> getPerks() {
		return perkCollection;
	}
	
	
	public void setName(String name) {
		if(name != null)
			this.name = name;
		else throw new NullPointerException("setName(String) cannot accept null.");
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public void setFileName() {
		this.fileName = this.name + FILE_SUFFIX;
	}
	
	
	public String getFileName() {
		return this.fileName;
	}
}

