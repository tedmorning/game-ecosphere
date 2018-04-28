package com.gameco.moves;

import java.time.Duration;

import com.gameco.GameMove;

public class StartSignal extends GameMove {

	public StartSignal(int num) {
		super(num, Duration.ZERO);
	}
	
	public StartSignal(int num, Duration zero) {
		super(num, Duration.ZERO);
	}
	
}
