package com.gameco.client;

public interface IClientContext {
	
	public void setConnection(String login, String pass) throws Exception;
	
	public String getUser();
	
	public void setUser(String user);
	
	public Object getGame();
	
	public void setGame(Object game);
	
	public Object getService();
	
}
