package com.gameco.games.chess;

public class Coord implements Cloneable {

	private int column;
	private int row;
	
	/**
	 * @param column
	 * @param row
	 */
	public Coord(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public Coord(Coord coord) {
		this.setCoord(coord);
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCoord(Coord c) {
		this.column = c.column;
		this.row = c.row;
	}
	
	public void move(Direction dir) {
		switch (dir) {
			case N:
				this.setRow(row+1);
				break;
			case S:
				this.setRow(row-1);
				break;
			case W:
				this.setColumn(column-1);
				break;
			case E:
				this.setColumn(column+1);
				break;
			case NW:
				this.setRow(row+1);
				this.setColumn(column-1);
				break;
			case NE:
				this.setRow(row+1);
				this.setColumn(column+1);
				break;
			case SE:
				this.setRow(row-1);
				this.setColumn(column+1);
				break;
			case SW:
				this.setRow(row-1);
				this.setColumn(column-1);
				break;
			default:
				break;
		}
	}
	
	public static Coord parse(String s) {
		int row = (int)(s.charAt(1)-'0') - 1;
		int col = (int) (s.charAt(0)-'a');
		return new Coord(col, row);
	}
	
	@Override
	public String toString() {
		char b = (char) ('a'+this.column);
		return String.format("%s%d", b+"", this.row+1);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}
		Coord coord = (Coord) obj;
		if ((coord.column == this.column) & (coord.row == this.row))
			return true;
		else
			return false;
	}
	
	@Override
	public Coord clone() {
		return new Coord(this.column, this.row);
	}
	
}
