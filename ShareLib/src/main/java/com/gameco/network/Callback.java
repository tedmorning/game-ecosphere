package com.gameco.network;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;

@XmlType(name="callback")
@XmlRootElement
public abstract class Callback {
	
	
	public abstract void invite(String[] players, String gameID);
	
	public abstract void cancel();
	
	public abstract void setMove(GameMove move);
	
	public abstract void setMove(GameHistory moves);
	
	public abstract void setInfo(String serverInfo);
	
}
