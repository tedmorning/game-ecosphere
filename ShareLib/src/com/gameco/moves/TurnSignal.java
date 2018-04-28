package com.gameco.moves;

import java.time.Duration;

import com.gameco.GameMove;

/**
 * Notify who must step now in turn-base games.
 */
public class TurnSignal extends GameMove{
	
	protected String nextPlayer;

	public TurnSignal(int num, Duration timePassed) {
		super(num, timePassed);
		this.nextPlayer = null;
	}
	
	public void setTurnOf(String turnof) {
		this.nextPlayer = turnof;
	}
	
	public String getNext() {
		return this.nextPlayer;
	}
	
}
