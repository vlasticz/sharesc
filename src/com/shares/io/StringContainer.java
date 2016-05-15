package com.shares.io;

import java.io.Serializable;

public class StringContainer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String string;
	
	public StringContainer() {}
	
	public StringContainer(String string) {
		this.string = Base64Coder.encodeString(string);		
	}
	
	public void setString(String string) {
		this.string = Base64Coder.encodeString(string);		
	}
	
	public String getString() {
		return Base64Coder.decodeString(string);
	}

}
