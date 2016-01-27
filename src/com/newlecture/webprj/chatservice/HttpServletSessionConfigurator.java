package com.newlecture.webprj.chatservice;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class HttpServletSessionConfigurator extends ServerEndpointConfig.Configurator{
	
	@Override
	public void modifyHandshake(ServerEndpointConfig config, 
			HandshakeRequest request, HandshakeResponse response) {
		//super.modifyHandshake(sec, request, response);
		
		HttpSession httpSession=(HttpSession)request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(),httpSession);
	}
}
