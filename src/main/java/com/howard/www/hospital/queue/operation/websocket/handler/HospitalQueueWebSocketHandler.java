package com.howard.www.hospital.queue.operation.websocket.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.howard.www.core.base.util.FrameworkStringUtils;

public class HospitalQueueWebSocketHandler extends TextWebSocketHandler {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueWebSocketHandler.class);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		log.info("afterConnectionEstablished(WebSocketSession session):"+FrameworkStringUtils.asString(session.getAttributes().get("sreenDeivce"))); 
		super.afterConnectionEstablished(session);
	}

	
}
