package com.gameco.server;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.EndpointReferenceUtils;

import com.gameco.User;
import com.gameco.game.GameHistory;
import com.gameco.game.GameMove;
import com.gameco.network.CredentialException;
import com.gameco.network.Callback;

@WebService(targetNamespace = "http://server.gameco.com/",
			portName = "GameExchangeServicePort",
			serviceName = "GameExchangeService", endpointInterface = "com.gameco.server.IGameExchangeService")
public class GameExchangeService implements IGameExchangeService {
	
	DataContainer data;
	
	public GameExchangeService() {
		data = DataContainer.getInstance();
	}
	
	@Resource
	WebServiceContext wsContext;
	
	@WebMethod
	public List<User> getUsers() {
		List<User> players = new ArrayList<>();
		for (Entry<String, User>  x: this.data.users.entrySet()) {
			players.add(x.getValue());
		}
		return players;
	}
	
	@WebMethod
	public User login(String username, String passwordhash) throws CredentialException {
		User u = this.data.db.getUser(username, passwordhash);
		this.data.users.put(u.getUsername(), u);
		return u;
	}
	
	@WebMethod
	public void logout() {
		User user = getUser(wsContext.getMessageContext());
		this.data.getLogout(user);
	}
	
	@WebMethod
	public void accept(String gameID) {
		User u = getUser(wsContext.getMessageContext());
		this.data.getAccept(gameID, u);
	}
	
	@WebMethod
	public void cancel(String gameID) {
		this.data.getCancel(gameID, getUser(wsContext.getMessageContext()));
	}
	
	@WebMethod
	public void join(String gameID) {
		this.data.getJoin(gameID, getUser(wsContext.getMessageContext()));
	}
	
	@WebMethod
	public void invite(String[] players) {
		this.data.tryCreate(players);
	}

	@WebMethod
	public void setMove(GameMove move) {
		User user = getUser(wsContext.getMessageContext());
		user.getGame().setMove(move);
	}

	@WebMethod
	public void setMoves(GameHistory moves) {
		User user = getUser(wsContext.getMessageContext());
		user.getGame().setMoves(moves);
	}
	
	@WebMethod
	public void addCallback(EndpointReferenceType callback) {
		User user = getUser(wsContext.getMessageContext());
		try {
			String wsdlLocation = EndpointReferenceUtils.getWSDLLocation(callback);
			QName serviceName = EndpointReferenceUtils.getServiceName(callback, null);	
			URL wsdlURL = new URL(wsdlLocation);			
			Service service = Service.create(wsdlURL, serviceName);
			Callback port = service.getPort(Callback.class);
			user.setCallback(port);
        } catch (Exception ex) {
			ex.printStackTrace();
        }
        
	}
	
	protected User getUser(MessageContext context) {
		@SuppressWarnings("rawtypes")
		Map http_headers = (Map) context.get(MessageContext.HTTP_REQUEST_HEADERS);
		@SuppressWarnings("rawtypes")
		List users = (List) http_headers.get("user");
		String username = (users != null)?users.get(0).toString():"";
		return this.data.users.get(username);
	}

	@WebMethod
	public List<String> getAvaibleGames() {
		return null;
	}
	
	@WebMethod
	public List<PlayingGame> getGames() {
		List<PlayingGame> games = new LinkedList<PlayingGame>();
		for (Entry<String, GameServerContainer> xy : this.data.playingGames.entrySet()) {
			games.add(new PlayingGame(xy.getKey(), xy.getValue().getPlayers()));
		}
		return games;
	}

}
