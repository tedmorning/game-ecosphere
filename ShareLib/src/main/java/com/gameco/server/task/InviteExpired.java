package com.gameco.server.task;

import com.gameco.User;
import com.gameco.network.Task;

public class InviteExpired extends Task {

	public InviteExpired(String gameID, User user) {
		super(gameID, user);
	}
	
}
