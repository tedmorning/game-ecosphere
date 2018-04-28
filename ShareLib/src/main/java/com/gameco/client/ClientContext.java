package com.gameco.client;

import javax.xml.ws.Service;

import com.gameco.Game;
import com.gameco.User;

public class ClientContext {
	
	protected Service service;

	protected static ClientContext instance = null;
	
	protected User user;
	protected Game game;
	
	public ClientContext() {
		this.game = null;
		this.user = null;
		this.service = null;
	}
	
	public static ClientContext getInstance() {
		if (instance == null) 
			instance = new ClientContext();
		return instance;
	}
	
	public void setConnection(Object ... args) {
		
	}
	
	public User getUser() {
		//return this.user;
		return new User("test", "0", null);
	}
	
	public void setUser(User user) {
		this.user =  user;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Service getService() {
		return this.service;
	}
	
}
