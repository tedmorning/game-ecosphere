package com.gameco.server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.gameco.Game;
import com.gameco.GameHistory;
import com.gameco.GameMove;
import com.gameco.IGameContainer;
import com.gameco.User;
import com.gameco.moves.EscapeSignal;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameServerContainer implements IGameContainer{
	
	private List<User> players;
	private List<User> watchers;
	
	private Game game;
	
	public GameServerContainer() {
		this.players = null;
		this.watchers = new ArrayList<User>();
		this.game = null;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void addWatcher(User user) {
		this.watchers.add(user);
	}
	
	public void userLeave(User user) {
		if (this.players.contains(user)) {
			this.players.remove(user);
			this.game.getMove(game.getMoveTemplate(EscapeSignal.class));
		}
		else if (this.watchers.contains(user)) {
			this.watchers.remove(user);
		}
	}

	@Override
	public void setMove(GameMove move) {
		for (User user : this.players) {
			user.getCallback().setMove(move);
		}
		for (User user : this.watchers) {
			user.getCallback().setMove(move);
		}
	}

	@Override
	public void setMoves(GameHistory moves) {
		for (User user : this.players) {
			user.getCallback().setMove(moves);
		}
		for (User user : this.watchers) {
			user.getCallback().setMove(moves);
		}
	}

	@Override
	public void invite(Class<Game> game, List<User> players) {
		this.players = players;
		for (User user : this.players) {
			user.getCallback().invite(game, players);
		}
	}

	@Override
	public void leave() {
		throw new NotImplementedException();
	}

}
