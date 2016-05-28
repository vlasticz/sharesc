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
	
	
	public boolean addPerk(String name) {
		
		if(name != null && name != "" && !isPerk(name)) {
			perkCollection.add(new Perk(name));			
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean removePerk(String name) {
		
		if(isPerk(name)) {			
			perkCollection.remove(getPerk(name));
			return true;
		} else {
			return false;
		}		
	}
	
	
	public boolean isPerk(String name) {
				
		if(getPerk(name) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public Perk getPerk(String name) {
		
		for(Perk p: perkCollection) {
			if(p.getName().equals(name))
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

