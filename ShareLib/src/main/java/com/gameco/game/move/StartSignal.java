package com.gameco.game.move;

//import java.time.Duration;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;

import com.gameco.game.GameMove;

//@XmlType( name = "startSignal")
//@XmlRootElement
public class StartSignal extends GameMove {
	
	public static GameMove newInstance(GameMove move) {
		move.setType(StartSignal.class.getSimpleName());
		return move;
	}

//	public StartSignal() {
//		super();
//		this.duration = Duration.ZERO;
//	}
//	
//	public StartSignal(int num) {
//		super(num, Duration.ZERO);
//	}
//	
//	public StartSignal(int num, Duration zero) {
//		super(num, Duration.ZERO);
//	}
	
}
