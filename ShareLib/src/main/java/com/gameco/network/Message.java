package com.gameco.network;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "username" }, name = "message")
@XmlRootElement
public class Message {
	
	@XmlTransient protected String username;

	public Message() {
		this.username = null;
	}
	
	public Message(String user) {
		this.username = user;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
