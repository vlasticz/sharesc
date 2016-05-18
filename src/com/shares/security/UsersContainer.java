/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.shares.security;

import com.shares.security.User;
import java.io.Serializable;
import java.util.ArrayList;

public class UsersContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;

	public UsersContainer(ArrayList<User> users) {
		this.users = users;
	}

	public UsersContainer() {
	}

	public ArrayList<User> getUsers() {
		return this.users;
	}
}