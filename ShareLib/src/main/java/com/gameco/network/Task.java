package com.gameco.network;

import com.gameco.User;

//@XmlType(propOrder = { "isCanceled", "gameID", "username" }, name = "task")
//@XmlRootElement
public abstract class Task {
	
	boolean isCanceled;
	String gameID;
	User username;
	
	public Task(String gameID, User user) {
		this.isCanceled = false;
		this.gameID = gameID;
		this.username = user;
	}
	
	public void cancel() {
		this.isCanceled = true;
	}
	
	public String getGameID() {
		return this.gameID;
	}
	
	public User getUser() {
		return this.username;
	}

}
