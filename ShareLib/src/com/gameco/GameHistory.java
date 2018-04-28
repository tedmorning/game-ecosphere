package com.gameco;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class GameHistory {
	
	ArrayList<GameMove> moves;
	
	public GameHistory() {
		this.moves = new ArrayList<GameMove>();
	}
	
	public List<GameMove> getMoves() {
		return this.moves;
	}
	
	public void append(GameHistory moves) {
		this.moves.addAll(moves.moves);
	}
	
	public void append(GameMove move) {
		this.moves.add(move);
	}
	
	public int size() {
		return this.moves.size();
	}

}
