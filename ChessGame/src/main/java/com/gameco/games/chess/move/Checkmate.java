package com.gameco.games.chess.move;

//import java.time.Duration;

import com.gameco.game.GameMove;

public class Checkmate extends GameMove {

	public static GameMove newInstance(GameMove move) {
		move.setType(Checkmate.class.getSimpleName());
		move.setMoves(null);
		return move;
	}
	
//	public Checkmate(int number, Duration timePassed) {
//		super(number, timePassed);
//	}
//	
//	@Override
//	public String toString() {
//		return this.getClass().getName();
//	}

}
