package com.howard.www.hospital.queue.operation.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;

@Component
public class WebSocketSubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketSubscribeListener.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		// TODO Auto-generated method stub
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
	
		logger.info("stomp Subscribe : "
				+ FrameworkStringUtils.asString(JSONObject.fromObject(headerAccessor.getMessageHeaders())));
	}

}
