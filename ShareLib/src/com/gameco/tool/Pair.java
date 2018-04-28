package com.gameco.tool;

public class Pair<X, Y> implements Cloneable {
	
	private X x;
	private Y y;
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(X x) {
		this.x = x;
	}
	
	public void setY(Y y) {
		this.y = y;
	}
	
	public X getX() {
		return this.x;
	}
	
	public Y getY() {
		return this.y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}
		else {
			Pair<?, ?> tmp = (Pair<?, ?>)obj;
			if ((!tmp.y.getClass().equals(this.y.getClass())) || (!tmp.x.getClass().equals(this.x.getClass()))) {
				return false;
			}
			else {
				if ((tmp.x == this.x) & (tmp.y == this.y)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %s)", this.x.toString(), this.y.toString());
	}
	
	@Override
	public Pair<X, Y> clone() {
		return new Pair<X, Y>(x, y);
	}

}
