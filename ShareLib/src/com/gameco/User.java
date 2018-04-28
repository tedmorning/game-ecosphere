package com.gameco;

import javax.xml.bind.annotation.*;
import com.gameco.network.UserCallback;

@XmlRootElement
public class User {
	
	@XmlElement
	private String name;
	@XmlElement
	private String sessionId;
	
	private UserCallback callback;
	
	@XmlElement
	String[] avaibleGame;
	Game currentGame;
	
	public User(String name, String sessionID, String[] agame) {
		this.name = name;
		this.sessionId = sessionID;
		this.avaibleGame = agame;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSessionID() {
		return this.sessionId;
	}
	
	public void setCallback(UserCallback callback) {
		this.callback = callback;
	}
	
	public UserCallback getCallback() {
		return this.callback;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		User usr = (User) obj;
		if (usr.name.equals(this.name)) {
			return true;
		}
		else {
			return false;
		}
	}

}
