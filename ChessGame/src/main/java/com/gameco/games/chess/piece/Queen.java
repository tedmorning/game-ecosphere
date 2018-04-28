package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class Queen extends Piece {

	public Queen(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public Queen(boolean isWhite) {
		super(isWhite);
	}

}
