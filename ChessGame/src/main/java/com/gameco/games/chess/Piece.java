package com.gameco.games.chess;

public abstract class Piece {
	
	private Coord pos;
	private boolean isWhite;
	
	public Piece(Coord pos, boolean isWhite) {
		this.pos = pos;
		this.isWhite = isWhite;
	}
	
	public Piece(boolean isWhite) {
		this.pos = null;
		this.isWhite = isWhite;
	}
	
	public Coord getPos() {
		return this.pos;
	}
	
	public void setPos(Coord pos) {
		this.pos = pos;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	public static boolean canBe(Coord pos) {
		if ((pos.getColumn() < 8) & (pos.getColumn() > -1) & (pos.getRow() < 8) & (pos.getRow() > -1))
			return true;
		else
			return false;
	}
	
	public boolean canMove(Direction dir) {
		switch (dir) {
		case N:
			if (pos.getRow() < 7)
				return true;
			return false;
		case S:
			if (pos.getRow() > 0)
				return true;
			return false;
		case W:
			if (pos.getColumn() > 0)
				return true;
			return false;
		case E:
			if (pos.getColumn() < 7)
				return true;
			return false;
			
		case NW:
			if ((pos.getRow() < 7) & (pos.getColumn() > 0))
				return true;
			return false;
		case SW:
			if ((pos.getRow() > 0) & (pos.getColumn() > 0))
				return true;
			return false;
		case NE:
			if ((pos.getRow() < 7) & (pos.getColumn() < 7))
				return true;
			return false;
		case SE:
			if ((pos.getRow() > 0) & (pos.getColumn() < 7))
				return true;
			return false;
		default:
			return false;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Piece fig = (Piece) obj;
		if (fig.getPos().equals(this.getPos())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" "+this.getPos().toString();
	}
	
}
