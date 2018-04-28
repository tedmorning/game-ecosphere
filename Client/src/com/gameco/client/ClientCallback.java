package com.gameco.client;

import com.gameco.Game;
import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;
import com.gameco.network.Callback;

@javax.jws.WebService(serviceName = "CallbackService", 
		portName = "CallbackPort",
		endpointInterface = "org.apache.callback.CallbackPortType",
		targetNamespace = "http://apache.org/callback")
public class ClientCallback extends Callback {
	
	ClientContext context;
	
	public ClientCallback(ClientContext context) {
		this.context = context;
	}

	@Override
	public void invite(String[] players, String gameID) {
		context.invite(players, gameID);
	}

	@Override
	public void cancel() {
		context.cancel();
	}

	@Override
	public void setMove(GameMove move) {
		Game game = (Game) context.getGame();
		game.getMove(move);
	}

	@Override
	public void setMove(GameHistory moves) {
		Game game = (Game) context.getGame();
		game.getMove(moves);
	}

	@Override
	public void setInfo(String serverInfo) {
		// TODO Auto-generated method stub
		
	}
	
	

}
