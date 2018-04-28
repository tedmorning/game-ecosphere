package com.gameco.server.task;

import com.gameco.User;
import com.gameco.network.Task;

public class MoveExpired extends Task {
	
	public MoveExpired(String gameID, User player) {
		super(gameID, player);
	}
	
}
