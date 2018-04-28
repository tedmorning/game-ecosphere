package com.gameco.game.move;

//import java.time.Duration;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;

import com.gameco.game.GameMove;

/**
 * Signal about wanted of one of players to leave this game.
 * Receiver always is master.
 */
//@XmlType(propOrder = { "escaper" }, name = "escapeSignal")
//@XmlRootElement
public class EscapeSignal extends GameMove {

	public static GameMove newInstance(GameMove move, String escaper) {
		move.setType(EscapeSignal.class.getSimpleName());
		String[] t = new String[0];
		t[0] = escaper;
		move.setMoves(t);
		return move;
	}
	
	public static String getEscaper(GameMove move) {
		return move.getMoves()[0];
	}
	
//	@XmlTransient protected String escaper;
//	
//	public EscapeSignal() {
//		super();
//		this.escaper = null;
//	}
//	
//	public EscapeSignal(int num, Duration timePassed, String escaper) {
//		super(num, timePassed);
//		this.escaper = null;
//	}
//	
//	public void setEscaper(String escaper) {
//		this.escaper = escaper;
//	}
//	
//	public String getEscaper() {
//		return this.escaper;
//	}
	
}
