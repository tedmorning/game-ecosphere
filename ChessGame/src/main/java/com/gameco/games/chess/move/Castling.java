package com.gameco.games.chess.move;

//import java.time.Duration;

import com.gameco.game.GameMove;
//import com.gameco.game.move.EndSignal;

public class Castling extends GameMove {

	public static GameMove newInstance(GameMove move, String side, boolean isLong) {
		move.setType(Castling.class.getSimpleName());
		String[] t = new String[2];
		t[0] = side;
		t[1] = Boolean.toString(isLong);
		move.setMoves(t);
		return move;
	}
	
	public static String getSide(GameMove move) {
		return move.getMoves()[0];
	}
	
	public static boolean isLong(GameMove move) {
		return Boolean.valueOf(move.getMoves()[1]);
	}
	
//	private String side;
//	private boolean isLong;
	
//	public Castling(int number, Duration timePassed, String side, boolean isLong) {
//		super(number, timePassed);
//		this.side = null;
//	}
//	
//	public void setSide(String side) {
//		this.side = side;
//	}
//	
//	public void setIsLong(boolean isLong) {
//		this.isLong = isLong;
//	}
//	
//	public String getSide() {
//		return this.side;
//	}
//	
//	public boolean getIsLong() {
//		return this.isLong;
//	}
//	
//	@Override
//	public String toString() {
//		return (isLong)?"O-O-O":"O-O";
//	}

}
