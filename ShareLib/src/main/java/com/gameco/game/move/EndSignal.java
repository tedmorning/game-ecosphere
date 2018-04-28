package com.gameco.game.move;

import com.gameco.game.GameMove;

//import java.time.Duration;
//import java.util.List;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;
//
//import com.gameco.User;
//import com.gameco.game.GameMove;

/**
 * Notify about ending of game. Has lists of winners and losers.
 */
//@XmlType(propOrder = { "winners", "losers" }, name = "endSignal")
//@XmlRootElement
public class EndSignal {// extends GameMove {
	
	public static GameMove newInstance(GameMove move, String[] winners, String[] losers) {
		move.setType(EndSignal.class.getSimpleName());
		String[] t = new String[winners.length+losers.length+2];
		t[0] = winners.length+"";
		int last = 1;
		for (int i = 0; i < winners.length; i++) {
			t[last+i] = winners[i];
		}
		last += winners.length;
		for (int i = 0; i < losers.length; i++) {
			t[last+i] = losers[i];
		}
		move.setMoves(t);
		return move;
	}
	
	public static String[] getWinners(GameMove move) {
		String[] g = move.getMoves();
		int t = Integer.valueOf(g[0])+1;
		String[] r = new String[Integer.valueOf(g[t])];
		for (int i = 0; i < t; i++) {
			r[i] = g[i+1];
		}
		return r;
	}
	
	public static String[] getLosers(GameMove move) {
		String[] g = move.getMoves();
		int t = Integer.valueOf(g[0])+1;
		String[] r = new String[Integer.valueOf(g[t])];
		for (int i = 0; i < Integer.valueOf(g[t]); i++) {
			r[i] = g[t+i];
		}
		return r;
	}
	
//	@XmlTransient protected List<User> winners;
//	@XmlTransient protected List<User> losers;
//
//	public EndSignal() {
//		super();
//		this.winners = null;
//		this.losers = null;
//	}
//	
//	public EndSignal(int num, Duration timePassed) {
//		super(num, timePassed);
//		this.winners = null;
//		this.losers = null;
//	}
//	
//	public void setWinners(List<User> winners) {
//		this.winners = winners;
//	}
//	
//	public void setLosers(List<User> losers) {
//		
//		this.losers = losers;
//	}
//	
//	public List<User> getWinners() {
//		return this.winners;
//	}
//	
//	public List<User> getLosers() {
//		return this.losers;
//	}
	
}
