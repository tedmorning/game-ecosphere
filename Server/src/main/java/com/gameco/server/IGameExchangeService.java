package com.gameco.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.cxf.ws.addressing.EndpointReferenceType;

import com.gameco.User;
import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;
import com.gameco.network.CredentialException;

@WebService(name = "IGameExchangeService", targetNamespace = "http://server.gameco.com/")
public interface IGameExchangeService {

	@WebMethod(operationName = "getUsers", action = "urn:GetUsers")
	List<User> getUsers();

	@WebMethod(operationName = "login", action = "urn:Login")
	User login(String username, String passwordhash) throws CredentialException;

	@WebMethod(operationName = "logout", action = "urn:Logout")
	void logout();

	@WebMethod(operationName = "accept", action = "urn:Accept")
	void accept(String gameID);

	@WebMethod(operationName = "cancel", action = "urn:Cancel")
	void cancel(String gameID);

	@WebMethod(operationName = "join", action = "urn:Join")
	void join(String gameID);

	@WebMethod(operationName = "invite", action = "urn:Invite")
	void invite(String[] players);

	@WebMethod(operationName = "setMove", action = "urn:SetMove")
	void setMove(GameMove move);

	@WebMethod(operationName = "setMoves", action = "urn:SetMoves")
	void setMoves(GameHistory moves);

	@WebMethod(operationName = "addCallback", action = "urn:AddCallback")
	void addCallback(EndpointReferenceType callback);

	@WebMethod(operationName = "getAvaibleGames", action = "urn:GetAvaibleGames")
	List<String> getAvaibleGames();

	@WebMethod(operationName = "getGames", action = "urn:GetGames")
	List<PlayingGame> getGames();

}