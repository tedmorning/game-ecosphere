package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class Rook extends Piece {

	public Rook(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public Rook(boolean isWhite) {
		super(isWhite);
	}

}
