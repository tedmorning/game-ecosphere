package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class Bishop extends Piece {

	public Bishop(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public Bishop(boolean isWhite) {
		super(isWhite);
	}
	
}
