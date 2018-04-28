package com.gameco;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.time.OffsetTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.gameco.server.GameServerContainer;

public abstract class Game {

	protected List<User> players;
	
	/**
	 * True - master, false - slave.
	 */
	protected boolean rank;
	protected IGameContainer outer;
	
	protected int turn;
	protected int number;
	
	protected OffsetTime started;
	protected Duration lastMoveTime;
	
	/**
	 * Null for slave version/
	 */
	protected GameHistory localHistory;
	protected GameHistory serverHistory;
	
	public Game(boolean rank) {
		this.rank = rank;
		this.number = 0;
		this.started = OffsetTime.now();
		this.lastMoveTime = Duration.ZERO;
		this.turn = 0;
	}
	
	protected int getNumberOfStep() {
		return this.number++;
	}
	
	protected Duration getStepDuration() {
		this.lastMoveTime = Duration.of(this.started.getSecond(), ChronoUnit.SECONDS);
		return this.lastMoveTime;
	}
	
	/**
	 * Any class inherited from GameMove with same constructor.
	 * @param type - class of custom GameMove
	 * @return Instance of given class
	 */
	public GameMove getMoveTemplate(Class<?> type) {
		try {
			Constructor<?> contructor = type.getConstructor(Integer.class, Duration.class);
			return (GameMove) contructor.newInstance(getNumberOfStep(), getNumberOfStep());
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public int nextTurnTo() {
		this.turn = (this.turn+1)%this.players.size();
		return this.turn;
	}
	
	public void setPlayers(List<User> players) {
		this.players = players;
	}
	
	/**
	 * Set container, which is wrapper of this game. All requests are redirected to wrapper
	 * which must communicate with all clients.
	 * @param container
	 */
	public void setContainer(IGameContainer container) {
		this.outer = container;
	}
	
	/**
	 * Processing of received move from other members.
	 * @param move - given move
	 */
	public void getMove(GameMove move) {
		if (this.rank) { // Server
			GameHistory steps = processMove(move);
			this.outer.setMoves(steps);
			this.serverHistory.append(steps);
		}
		else { // client
			this.serverHistory.append(processMove(move));
		}
	}
	
	/**
	 * Processing of received moves from other members.
	 * @param move - given moves
	 */
	public void getMove(GameHistory moves) {
		if (this.rank) { // Server
			GameHistory steps = new GameHistory();
			for (GameMove move : moves.getMoves()) {
				steps.append(processMove(move));
			}
			this.outer.setMoves(steps);
			this.serverHistory.append(steps);
		}
		else { // client
			for (GameMove move : moves.getMoves()) {
				this.serverHistory.append(processMove(move));
			}
		}
	}
	
	public abstract GameHistory processMove(GameMove move);
	
	public static IGameContainer getWrapper(boolean isMaster) {
		return (isMaster)?new GameServerContainer():new GameServerContainer();
	}
	
}
