package com.shares.io;

import java.io.Serializable;
import java.util.ArrayList;

import com.shares.base.Core;
import com.shares.security.User;

public class DataContainer implements Serializable {

	
	private static final long serialVersionUID = -9118414651395927164L;
	
	private ArrayList<User> users;
	private Core core;
	

	public DataContainer(ArrayList<User> users, Core core) {
		this.users = users;
		this.core = core;
	}
	

	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	
	public Core getCore() {
		return this.core;
	}

}
