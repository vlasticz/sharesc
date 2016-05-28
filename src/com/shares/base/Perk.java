package com.shares.base;

import java.io.Serializable;

public class Perk implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, caption;	
	private int value;
	
		
	public Perk(String name) {
		
		if(name != null) {
			this.value = 0;			
			this.name = name;
			this.caption = name;
						
		}
	}
	
	
	public Perk(String name, String caption) {
				
		if(name != null & caption != null) {
			this.value = 0;			
			this.name = name;
			this.caption = caption;			
		}
	}
	
	
	public String getName() {
		
		return this.name;
	}
	
	
	public String getCaption() {
				
		return this.caption;		
	}
	
	
	public void setCaption(String caption) {
		
		if(caption != null)
			this.caption = caption;
	}
	
	
	public int getValue() {
		
		return this.value;
	}
	
	
	public boolean addValue() {
		
		this.value += 1;
		return true;
	}
	
	
	public boolean subValue() {
		
		if(this.value > 0) {
			this.value -= 1;
			return true;
		} else {
			return false;
		}
	}
}
