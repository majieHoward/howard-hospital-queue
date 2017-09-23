package com.howard.www.hospital.queue.operation.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;

public class HospitalQueueWebSocketHandler implements WebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(HospitalQueueWebSocketHandler.class);
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		logger.info("afterConnectionEstablished-HospitalQueueWebSocketHandler");
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		logger.info("handleMessage-HospitalQueueWebSocketHandler:"+FrameworkStringUtils.asString(JSONObject.fromObject(message)));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		logger.info("handleTransportError-HospitalQueueWebSocketHandler");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		logger.info("afterConnectionClosed-HospitalQueueWebSocketHandler");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
