package com.gameco.game;

import java.time.Duration;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType
@XmlType(propOrder = { "number", "duration", "type", "moves" }, name = "gameMove")
@XmlRootElement
public class GameMove {
	
	@XmlTransient protected int number;
	@XmlTransient protected Duration duration;
	
	@XmlTransient protected String type;
	@XmlTransient protected String[] moves;
	
	public GameMove() {
		this.number = -1;
		this.duration = null;
	}
	
	public GameMove(int number, Duration timePassed) {
		this.number = number;
		this.duration = timePassed;
	}
	
	public GameMove(int number, Duration timePassed, String type, String[] moves) {
		this.number = number;
		this.duration = timePassed;
		this.type = type;
		this.moves = moves;
	}
	
	public void setNumber(int num) {
		this.number = num;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setDuration(Duration d) {
		this.duration = d;
	}
	
	public Duration getDuration() {
		return this.duration;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setMoves(String[] moves) {
		this.moves = moves;
	}

	public String[] getMoves() {
		return this.moves;
	}
	
}
