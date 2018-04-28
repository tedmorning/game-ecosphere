package com.gameco.server;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.gameco.User;

@XmlType(propOrder = { "gameID", "players" }, name = "playingGame")
@XmlRootElement
public class PlayingGame {

	@XmlTransient protected String gameID;
	@XmlTransient protected List<User> players;
	
	public PlayingGame() {
		this.gameID = null;
		this.players = null;
	}
	
	public PlayingGame(String gameID, List<User> players) {
		this.gameID = gameID;
		this.players = players;
	}
	
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	
	public String getGameID() {
		return this.gameID;
	}

	public void setPlayers(List<User> pls) {
		this.players = pls;
	}
	
	public List<User> getPlayers() {
		return this.players;
	}
	
}
