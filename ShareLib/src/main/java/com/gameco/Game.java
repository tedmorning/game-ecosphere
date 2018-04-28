package com.gameco;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;

import javafx.scene.Parent;

public abstract class Game {

	protected List<User> players;
	
	/**
	 * True - master, false - slave.
	 */
	protected boolean rank;
	/**
	 * Master through it receive moves, slave send it.
	 */
	protected GameContainer outer;
	
	/**
	 * Index of player who moving in players list.
	 */
	protected int turn;
	protected int number;
	
	protected OffsetTime started;
	protected Duration lastMoveTime;
	
	protected GameHistory localHistory;
	protected GameHistory serverHistory;
	
	protected GameContainer container;
	
	public Game(boolean rank) {
		this.rank = rank;
		this.number = 0;
		this.started = OffsetTime.now();
		this.lastMoveTime = Duration.ZERO;
		this.turn = 0;
		this.serverHistory = new GameHistory();
//		this.container = contianer;
	}
	
	public int getNumberOfStep() {
		return this.number++;
	}
	
	public Duration getStepDuration() {
		this.lastMoveTime = Duration.of(this.started.getSecond(), ChronoUnit.SECONDS);
		return this.lastMoveTime;
	}
	
	public int nextTurnTo() {
		this.turn = (this.turn+1)%this.players.size();
		return this.turn;
	}
	
	public void setPlayers(List<User> players) {
		this.players = players;
	}
	
	public List<User> getPlayers() {
		return this.players;
	}
	
	/**
	 * Set container, which is wrapper of this game. All requests are redirected to wrapper
	 * which must communicate with all clients.
	 * @param container
	 */
	public void setContainer(GameContainer container) {
		this.outer = container;
	}
	
	/**
	 * Processing of received move from other members.
	 * @param move - given move
	 */
	public void getMove(GameMove move) {
		if (this.rank) { // Server
			GameHistory steps = processMoveServer(move);
			this.outer.setMoves(steps);
			this.serverHistory.append(steps);
		}
		else { // client
			this.serverHistory.append(processMoveClient(move));
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
				steps.append(processMoveServer(move));
			}
			this.outer.setMoves(steps);
			this.serverHistory.append(steps);
		}
		else { // client
			for (GameMove move : moves.getMoves()) {
				this.serverHistory.append(processMoveClient(move));
			}
		}
	}
	
	public abstract GameHistory processMoveClient(GameMove move);
	
	public abstract GameHistory processMoveServer(GameMove move);
	
	public abstract void initiateMoveClient(GameMove move);
	
	public GameContainer getWrapper() {
		return this.container;
	}
	
	public GameHistory getContext() {
		return this.serverHistory;
	}
	
	public abstract Parent getView() throws IOException;
	
	public abstract void switchTurn();
	
}
