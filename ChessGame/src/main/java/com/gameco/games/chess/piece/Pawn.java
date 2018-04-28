package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class Pawn extends Piece {

	public Pawn(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public Pawn(boolean isWhite) {
		super(isWhite);
	}

}
