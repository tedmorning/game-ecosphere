package com.gameco;

import java.time.Duration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameMove {
	
	protected int number;
	protected Duration timePassed;
	
	public GameMove(int number, Duration timePassed) {
		this.number = number;
		this.timePassed = timePassed;
	}
	
	public int getNumber() {
		return this.number;
	}

}
