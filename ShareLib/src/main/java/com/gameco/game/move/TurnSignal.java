package com.gameco.game.move;

//import java.time.Duration;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;

import com.gameco.game.GameMove;

/**
 * Notify who must step now in turn-base games.
 */
//@XmlType(propOrder = { "turnOf" }, name = "turnSignal")
//@XmlRootElement
public class TurnSignal extends GameMove{
	
	public static GameMove newInstance(GameMove move, String turnOf) {
		move.setType(TurnSignal.class.getSimpleName());
		String[] t = new String[0];
		t[0] = turnOf;
		move.setMoves(t);
		return move;
	}
	
	public static String getEscaper(GameMove move) {
		return move.getMoves()[0];
	}
	
//	@XmlTransient protected String turnOf;
//
//	public TurnSignal() {
//		super();
//		this.turnOf = null;
//	}
//	
//	public TurnSignal(int num, Duration timePassed) {
//		super(num, timePassed);
//		this.turnOf = null;
//	}
//	
//	public void setTurnOf(String turnof) {
//		this.turnOf = turnof;
//	}
//	
//	public String getTurnOf() {
//		return this.turnOf;
//	}
	
}
