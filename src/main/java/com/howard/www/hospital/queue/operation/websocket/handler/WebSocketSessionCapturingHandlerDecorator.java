package com.howard.www.hospital.queue.operation.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

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

	/**
	 * 
	 * <p>Title: afterConnectionEstablished</p>   
	 * <p>Description: 在WebSocket协商成功并且WebSocket连接打开并可以使用之后调用</p>   
	 * @param session
	 * @throws Exception   
	 * @see org.springframework.web.socket.handler.WebSocketHandlerDecorator#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
	 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		/**
		 * 在WebSocket协商成功并且WebSocket连接打开并可以使用之后调用
		 */
		super.afterConnectionEstablished(session);
	}

	/**
	 * 
	 * <p>Title: handleMessage</p>   
	 * <p>Description: 当新的WebSocket消息到达时调用</p>   
	 * @param session
	 * @param message
	 * @throws Exception   
	 * @see org.springframework.web.socket.handler.WebSocketHandlerDecorator#handleMessage(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.WebSocketMessage)
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		/**
		 * 当新的WebSocket消息到达时调用
		 */
		super.handleMessage(session, message);
	}

	/**
	 * 
	 * <p>Title: handleTransportError</p>   
	 * <p>Description: 处理底层WebSocket消息传输中的错误</p>   
	 * @param session
	 * @param exception
	 * @throws Exception   
	 * @see org.springframework.web.socket.handler.WebSocketHandlerDecorator#handleTransportError(org.springframework.web.socket.WebSocketSession, java.lang.Throwable)
	 */
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		/**
		 * 处理底层WebSocket消息传输中的错误
		 */
		super.handleTransportError(session, exception);
	}

	/**
	 * 
	 * <p>Title: afterConnectionClosed</p>   
	 * <p>Description: 在WebSocket连接已经被任何一方关闭之后，或在发生传输错误之后调用</p>   
	 * @param session
	 * @param closeStatus
	 * @throws Exception   
	 * @see org.springframework.web.socket.handler.WebSocketHandlerDecorator#afterConnectionClosed(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.CloseStatus)
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		/**
		 * 在WebSocket连接已经被任何一方关闭之后，或在发生传输错误之后调用
		 */
		super.afterConnectionClosed(session, closeStatus);
	}
}
