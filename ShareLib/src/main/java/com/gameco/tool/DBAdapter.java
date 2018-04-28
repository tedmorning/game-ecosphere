package com.gameco.tool;

import com.gameco.User;
import com.gameco.network.CredentialException;

public class DBAdapter {
	
	public static int userCreated = 0;

	public User getUser(String name, String passhash) throws CredentialException {
		return new User(name, "4", new String[] {"Chess"});
	}
	
//	public User getUser(String name, String passhash) throws CredentialException {
//		return new User(name, System.currentTimeMillis()+""+name, new String[] {"Chess"});
//	}
	
}
