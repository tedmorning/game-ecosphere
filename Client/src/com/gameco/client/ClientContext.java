package com.gameco.client;

import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
//import javax.xml.ws.wsaddressing.W3CEndpointReference;
//
//import org.apache.cxf.ws.addressing.EndpointReferenceType;
//import org.apache.cxf.ws.addressing.EndpointReferenceUtils;

import com.gameco.Game;
import com.gameco.client.view.ManagerMessage;
import com.gameco.network.Callback;
import com.gameco.server.GameExchangeServicePortImpl;
import com.gameco.server.IGameExchangeService;

public class ClientContext implements IClientContext {
	
	protected GameExchangeServicePortImpl service;

	protected static ClientContext instance = null;
	
	protected String user;
	
	protected Game game;
	protected String gameID;
	protected boolean isInitiator;
	protected List<String> players;
	
	protected Callback callback;
	
	public ClientContext() {
		this.user = null;
		this.game = null;
		this.isInitiator = false;
		try {
			this.service = Service.create(new URL("http://localhost:9090/gameexchangeservice/gameexchangeservice?wsdl"), new QName("http://server.gameco.com/", "GameExchangeService")).getPort(GameExchangeServicePortImpl.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ClientContext getInstance() {
		if (instance == null) 
			instance = new ClientContext();
		return instance;
	}
	
	public void setConnection(String login, String pass) throws Exception {
		MessageDigest p = MessageDigest.getInstance("MD5");
		byte[] is = p.digest(pass.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < is.length; ++i) {
			sb.append(Integer.toHexString((is[i] & 0xFF) | 0x100).substring(1,3));
		}
		String password = sb.toString();
		this.user = service.login(login, password).getUsername();
		
//		QName SERVICE_NAME = new QName("http://apache.org/callback", "SOAPService");
//		QName SERVICE_NAME_CALLBACK = new QName("http://apache.org/callback", "CallbackService");
//		QName PORT_NAME_CALLBACK = new QName("http://apache.org/callback", "CallbackPort");
//		QName PORT_TYPE_CALLBACK = new QName("http://apache.org/callback", "CallbackPortType");
		
		Endpoint.publish("http://localhost:9085/CallbackContext/CallbackPort", new ClientCallback(this));
		
//		EndpointReferenceType ref = EndpointReferenceUtils.getEndpointReference(wsdlURL, SERVICE_NAME_CALLBACK, PORT_NAME_CALLBACK.getLocalPart());
//		EndpointReferenceUtils.setInterfaceName(ref, PORT_TYPE_CALLBACK);
		
//		service.addCallback(ref);
	     
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String object) {
		this.user = object;
	}
	
	public Object getGame() {
		return this.game;
	}
	
	public void setGame(Object game) {
		this.game = (Game) game;
	}
	
	public Object getService() {
		return this.service;
	}
	
	public GameExchangeServicePortImpl getServiceImpl() {
		return this.service;
	}
	
	public void invite(String[] players, String gameID) {
		this.players = Arrays.asList(players);
		if (this.players.contains(this.user)) {
			IGameExchangeService gg = this.service;
			gg.accept(this.gameID);
		}
		SceneManager.getInstance().changeLayout(ManagerMessage.INVITE);
	}
	
	public void cancel() {
		this.gameID = null;
		SceneManager.getInstance().changeLayout(ManagerMessage.MENU);
	}
	
	public boolean isInitiator() {
		return this.isInitiator;
	}
	
	public List<String> getPlayers() {
		return this.players;
	}
	
}
