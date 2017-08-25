package com.howard.www.hospital.queue.operation.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  WebSocketSessionCapturingHandlerDecorator   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年8月23日 下午11:00:38   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class WebSocketSessionCapturingHandlerDecorator extends WebSocketHandlerDecorator {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketSessionCapturingHandlerDecorator.class);

	public WebSocketSessionCapturingHandlerDecorator(WebSocketHandler delegate) {
		super(delegate);
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished");
		logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(session)));
		super.afterConnectionEstablished(session);
	}

	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info("handleMessage");
		super.handleMessage(session, message);
	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("handleTransportError");
		super.handleTransportError(session, exception);
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("afterConnectionClosed");
		super.afterConnectionClosed(session, closeStatus);
	}
}
