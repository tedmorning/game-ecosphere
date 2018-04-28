package com.gameco.moves;

import java.time.Duration;

import com.gameco.GameMove;

/**
 * Signal about wanted of one of players to leave this game.
 * Receiver always is master.
 */
public class EscapeSignal extends GameMove {

	protected String escaper;
	
	public EscapeSignal(int num, Duration timePassed, String escaper) {
		super(num, timePassed);
		this.escaper = null;
	}
	
	public void setName(String escaper) {
		this.escaper = escaper;
	}
	
	public String getName() {
		return this.escaper;
	}
	
}
