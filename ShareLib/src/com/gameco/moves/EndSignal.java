package com.gameco.moves;

import java.time.Duration;
import java.util.List;

import com.gameco.GameMove;
import com.gameco.User;

/**
 * Notify about ending of game. Has lists of winners and losers.
 */
public class EndSignal extends GameMove {
	
	protected List<User> winners;
	protected List<User> losers;

	public EndSignal(int num, Duration timePassed) {
		super(num, timePassed);
		this.winners = null;
		this.losers = null;
	}
	
	public void setWinners(List<User> winners) {
		this.winners = winners;
	}
	
	public void setLosers(List<User> losers) {
		
		this.losers = losers;
	}
	
	public List<User> getWinners() {
		return this.winners;
	}
	
	public List<User> getLosers() {
		return this.losers;
	}
	
}
