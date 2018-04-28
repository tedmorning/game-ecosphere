package com.gameco.tool;

import com.gameco.User;
import com.gameco.network.CredentialException;

public class DBAdapter {

	public User getUser(String name, String passhash) throws CredentialException {
		return new User(name, "4", new String[] {"Chess"});
	}
	
}
