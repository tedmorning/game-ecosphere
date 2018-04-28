package com.gameco.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.gameco.Game;
import com.gameco.User;
import com.gameco.game.GameMove;
import com.gameco.game.move.EscapeSignal;
import com.gameco.network.Task;
import com.gameco.server.task.InviteExpired;
import com.gameco.server.task.MoveExpired;
//import com.gameco.server.GameServerContainer;
//import com.gameco.server.task.InviteExpired;
//import com.gameco.server.task.MoveExpired;
import com.gameco.tool.DBAdapter;

public class DataContainer {

	protected static DataContainer instance = null;
	
	Map<String, User> users;
	Map<String, GameServerContainer> playingGames;
	
	Map<String, GameServerContainer> creatingGames;
	Map<String, List<User>> waiters;
	
	List<List<Task>> tasks;
	int currentBell;
	Timer bell;
	
	DBAdapter db;
	
	public static DataContainer getInstance() {
		if (instance == null)
			instance = new DataContainer();
		return instance;
	}
	
	protected DataContainer() {
		this.users = new HashMap<String, User>();
		this.playingGames = new HashMap<String, GameServerContainer>();
		this.creatingGames = new HashMap<String, GameServerContainer>();
		this.waiters = new HashMap<String, List<User>>();
		this.db = new DBAdapter();
		this.currentBell = 0;
		this.tasks = new ArrayList<List<Task>>(120);
		this.bell = new Timer(true);
		
		this.bell.schedule(new TimerTask() {
			
			@Override
			public void run() {
				for (Task task : tasks.get(currentBell)) {
					processTask(task);
				}
				tasks.get(currentBell).clear();
				currentBell = (currentBell+1) % tasks.size();
			}
		}, 1000);
	}
	
	protected void processTask(Task task) {
		if (task.getClass().equals(InviteExpired.class)) {
			InviteExpired t = (InviteExpired) task;
			this.playingGames.get(t.getGameID()).close();
			this.playingGames.remove(t.getGameID());
		}
		else if (task.getClass().equals(MoveExpired.class)) {
			MoveExpired t = (MoveExpired) task;
			GameServerContainer cont = (GameServerContainer) t.getUser().getGame();
			cont.getGame().switchTurn();
		}
	}
	
	public void setTask(Task task, int secondDelay) {
		this.tasks.get((secondDelay+currentBell) % this.tasks.size()).add(task);
	}
	
	public void tryCreate(String[] players) {
		String id = this.getGameID(players);
		GameServerContainer c = new GameServerContainer();
		this.creatingGames.put(id, c);
		ArrayList<User> ww = new ArrayList<>();
		ArrayList<User> ss = new ArrayList<>();
		boolean cant = false;
		for (String s : players) {
			User u = this.getUser(s);
			if (u.getGame() != null) {
				cant = true;
				break;
			}
			ww.add(u);
			ss.add(u);
		}
		if (!cant) { // Ó÷àñòíèêè íå çàíÿòû
			this.waiters.put(id, ww);
			c.setPlayers(ss);
			c.setGameID(id);
			String[] a = new String[ww.size()];
			ww.toArray(a);
			for (User u : ww) {
				u.getCallback().invite(a, id);
			}
			c.invite();
		}
		else { // Äðóãèå çàíÿòû
			this.getUser(players[0]).getCallback().cancel();
		}
	}
	
	public void getJoin(String gameID, User user) {
		this.playingGames.get(gameID).addWatcher(this.getUser(user.getUsername()));
	}
	
	public void getAccept(String gameID, User user) {
		this.waiters.get(gameID).remove(this.getUser(user.getUsername()));
		this.creatingGames.get(gameID).accept(user);
		if (this.waiters.get(gameID).isEmpty()) {
			this.playingGames.put(gameID, this.creatingGames.get(gameID));
			Game game = null;
			for (User u : this.playingGames.get(gameID).getPlayers()) {
				u.setGame(this.playingGames.get(gameID));
			}
			this.creatingGames.get(gameID).setGame(game);
		}
	}
	
	public void getCancel(String gameID, User user) {
		this.creatingGames.get(gameID).close();
		this.creatingGames.remove(gameID);
		List<User> ul = this.waiters.get(gameID);
		for (User u : ul) {
			u.getCallback().cancel();
		}
		this.waiters.remove(gameID);
	}
	
	public void getLogout(User user) {
		if (this.users.get(user.getUsername()).getGame() == null) {
			this.users.remove(user.getUsername());
		}
		else {
			this.users.get(this.getUser(user.getUsername())).getUsername();
			Game game = this.users.get(this.getUser(user.getUsername())).getGame().getGame();
			game.getMove(EscapeSignal.newInstance(new GameMove(game.getNumberOfStep(), game.getStepDuration()), user.getUsername()));
//			game.getMove(new EscapeSignal(game.getNumberOfStep(), game.getStepDuration(), user.getUsername()));
			this.users.remove(user.getUsername());
		}
	}
	
	public User getUser(String username) {
		return this.users.get(username);
	}
	
	public String getGameID(String ...users) {
		StringBuffer sb = new StringBuffer();
		for (String u : users) {
			sb.append(u);
		}
		return String.format("%s%s", System.currentTimeMillis()+"", sb.toString());
	}
	
}