package com.gameco;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;

/**
 * Wrapper of game for connection to others members.
 */
@XmlType(name="gameContainer")
@XmlRootElement
public abstract class GameContainer {

	/**
	 * Invoke by game to connect with other members.
	 * Send alone game move.
	 */
	public abstract void setMove(GameMove move);
	
	/**
	 * Invoke by game to connect with other members.
	 * Send some count of game moves.
	 */
	public abstract void setMoves(GameHistory moves);
	
	public abstract Game getGame();
	
	public abstract String getGameID();
	
}
