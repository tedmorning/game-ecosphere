package com.gameco.games.chess;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Square implements Observable {

	private List<InvalidationListener> listeners;
	
	protected Coord pos;
	protected Piece piece;
	protected boolean isWhite;
	
	public Square(Coord coord, boolean isWhite) {
		this.pos = coord;
		this.listeners = new LinkedList<InvalidationListener>();
		this.isWhite = isWhite;
	}
	
	public void setPiece(Piece fig) {
		this.piece = fig;
		if (fig != null)
			fig.setPos(this.pos);
		notifyListeners();
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPos(Coord pos) {
		this.pos = pos;
	}
	
	public Coord getPos() {
		return this.pos;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!this.getClass().equals(o.getClass())) {
			return false;
		}
		Square os = (Square) o;
		if (os.getPos().equals(this.getPos())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return isWhite?"white":"black"+" "+((this.getPiece() == null)?this.getPos().toString():this.piece.toString());
	}
	
	protected void notifyListeners() {
		for (InvalidationListener list : this.listeners) {
			list.invalidated(this);
		}
	}
	
	@Override
	public void addListener(InvalidationListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		this.listeners.remove(listener);
	}

}
