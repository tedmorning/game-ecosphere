package com.gameco.games.chess.move;

//import java.time.Duration;

import com.gameco.game.GameMove;

public class Check extends GameMove {
	
	public static GameMove newInstance(GameMove move) {
		move.setType(Check.class.getSimpleName());
		move.setMoves(null);
		return move;
	}

//	public static String getSide(GameMove move) {
//		return move.getMoves()[0];
//	}
//	
//	public Check(int number, Duration timePassed) {
//		super(number, timePassed);
//	}
//	
//	@Override
//	public String toString() {
//		return this.getClass().getName();
//	}

}
