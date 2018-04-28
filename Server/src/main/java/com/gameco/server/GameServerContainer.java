package com.gameco.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.gameco.Game;
import com.gameco.GameContainer;
import com.gameco.User;
import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;
import com.gameco.game.move.EscapeSignal;
import com.gameco.network.Task;
import com.gameco.server.task.InviteExpired;

public class GameServerContainer extends GameContainer{
	
	private List<User> players;
	private List<User> watchers;
	
	private List<Task> tasks;
	
	private String gameID;
	private Game game;
	
	public GameServerContainer() {
		this.players = null;
		this.watchers = new ArrayList<User>();
		this.game = null;
		this.tasks = new LinkedList<Task>();
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	
	public String getGameID() {
		return this.gameID;
	}
	
	public void addWatcher(User user) {
		this.watchers.add(user);
	}
	
	public void userLeave(User user) {
		if (this.players.contains(user)) {
			this.players.remove(user);
			this.game.getMove(EscapeSignal.newInstance(new GameMove(this.game.getNumberOfStep(), this.game.getStepDuration()), user.getUsername()));
//			this.game.getMove(new EscapeSignal(this.game.getNumberOfStep(), this.game.getStepDuration(), user.getUsername()));
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

	public void invite() {
		for (User user : this.players) {
			Task task = new InviteExpired(this.gameID, user);
			this.tasks.add(task);
			DataContainer.getInstance().setTask(task, 30);
		}
	}
	
	public void accept(User user) {
		for (Task t : this.tasks) {
			if (t.getClass().equals(InviteExpired.class) && t.getUser().equals(user)) {
				InviteExpired ietask = (InviteExpired) t;
				ietask.cancel();
			}
		}
	}
	
	public void cancel() {
		for (Task t : this.tasks) {
			t.cancel();
		}
		close();
	}
	
//	public void leave(String name) {
//		this.game.getMove(this.game.getMoveTemplate(EscapeSignal.class));
//	}

//	@Override
//	public void leave() {
//		throw new NotImplementedException();
//	}
	
	public void close() {
//		game.getMove(game.getMoveTemplate(FailedConnectionSignal.class));
		this.cancel();
		this.watchers.clear();
		this.players.clear();
		this.tasks.clear();
		this.game = null;
	}
	
	public void setPlayers(List<User> users) {
		this.players = users;
	}

	public List<User> getPlayers() {
		return this.players;
	}
	
	public List<User> getWatchers() {
		return this.watchers;
	}

}