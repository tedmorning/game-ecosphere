package com.gameco.network;

import java.util.List;

import com.gameco.Game;
import com.gameco.GameHistory;
import com.gameco.GameMove;
import com.gameco.User;

public interface UserCallback {

	void invite(Class<Game> game, List<User> players);
	
	void setMove(GameMove move);
	
	void setMove(GameHistory moves);
	
	void setInfo(String serverInfo);
	
}
