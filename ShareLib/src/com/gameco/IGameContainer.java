package com.gameco;

import java.util.List;

/**
 * Wrapper of game for connection to others members.
 */
public interface IGameContainer {

	/**
	 * Invoke by game to connect with other members.
	 * Send alone game move.
	 */
	void setMove(GameMove move);
	
	/**
	 * Invoke by game to connect with other members.
	 * Send some count of game moves.
	 */
	void setMoves(GameHistory moves);
	
	/**
	 * If game rank is master give offer to players, else do request to server.
	 * @param game - game type
	 * @param players - wanted players
	 */
	void invite(Class<Game> game, List<User> players);
	
	/**
	 * Only for games with client rank. Notify server that one of user want to leave this game.
	 */
	void leave();
	
}
