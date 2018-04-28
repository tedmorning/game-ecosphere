package com.gameco.games.chess.move;

//import java.time.Duration;

import com.gameco.game.GameMove;
import com.gameco.games.chess.Coord;
//import com.gameco.games.chess.Coord;

public class PieceMove extends GameMove {

	public static GameMove newInstance(GameMove move, String side, Coord from, Coord to) {
		move.setType(PieceMove.class.getSimpleName());
		String[] t = new String[3];
		t[0] = side;
		t[1] = from.toString();
		t[2] = to.toString();
		move.setMoves(t);
		return move;
	}
	
	public static String getSide(GameMove move) {
		return move.getMoves()[0];
	}
	
	public static Coord getFrom(GameMove move) {
		return Coord.parse(move.getMoves()[1]);
	}
	
	public static Coord getTo(GameMove move) {
		return Coord.parse(move.getMoves()[2]);
	}
	
//	private Coord from;
//	private Coord to;
//	private String side;
//	
//	public PieceMove(int number, Duration timePassed, Coord from, Coord to, String who) {
//		super(number, timePassed);
//		this.from = from;
//		this.to = to;
//		this.side = who;
//	}
//	
//	public void setFigureCoord(Coord from) {
//		this.from = from;
//	}
//	
//	public void setDestination(Coord to) {
//		this.to = to;
//	}
//	
//	public void setPlayer(String side) {
//		this.side = side;
//	}
//	
//	public Coord getFigureCoord() {
//		return this.from;
//	}
//	
//	public Coord getDestination() {
//		return this.to;
//	}
//	
//	public String getPlayer() {
//		return this.side;
//	}
//	
//	@Override
//	public String toString() {
//		return String.format("%s %s", this.from.toString(), this.to.toString());
//	}

}
