package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class King extends Piece {

	public King(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public King(boolean isWhite) {
		super(isWhite);
	}

}
