package com.gameco.games.chess.piece;

import com.gameco.games.chess.Coord;
import com.gameco.games.chess.Piece;

public class Knight extends Piece {

	public Knight(Coord pos, boolean isWhite) {
		super(pos, isWhite);
	}
	
	public Knight(boolean isWhite) {
		super(isWhite);
	}

}
