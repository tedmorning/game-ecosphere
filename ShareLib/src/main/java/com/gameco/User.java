package com.gameco;

import javax.xml.bind.annotation.*;
import com.gameco.network.Callback;

@XmlType(propOrder = { "username", "sessionID", "avaibleGame", "callback", "game"}, name = "user")
@XmlRootElement
public class User {
	
	@XmlTransient private String username;
	@XmlTransient private String sessionId;
	
	@XmlTransient private Callback callback;
	
	@XmlTransient private String[] avaibleGame;
	
	@XmlTransient private GameContainer game;
	
	public User() {
		this.username = null;
		this.sessionId = null;
		this.avaibleGame = null;
		this.callback = null;
	}
	
	public User(String name, String sessionID, String[] agame) {
		this.username = name;
		this.sessionId = sessionID;
		this.avaibleGame = agame;
		this.callback = null;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setSessionID(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSessionID() {
		return this.sessionId;
	}
	
	public void setAvaibleGame(String[] avG) {
		this.avaibleGame = avG;
	}
	
	public String[] getAvaibleGame() {
		return this.avaibleGame;
	}
	
	public void setCallback(Callback callback) {
		this.callback = callback;
	}
	
	public Callback getCallback() {
		return this.callback;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		User usr = (User) obj;
		if (usr.username.equals(this.username)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setGame(GameContainer game) {
		this.game = game;
	}
	
	public GameContainer getGame() {
		return this.game;
	}

}
