package com.howard.www.hospital.queue.operation.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

/**
 * 
 * @ClassName:  WebSocketConnectListener   
 * @Description:TODO Event raised when a new WebSocket client using a Simple Messaging Protocol (e.g. STOMP) as the WebSocket sub-protocol issues a connect request.
 *Note that this is not the same as the WebSocket session getting established but rather the client's first attempt to connect within the sub-protocol, for example sending the STOMP CONNECT frame.  
 *当使用简单消息传递协议(例如STOMP)作为WebSocket子协议的新WebSocket客户端发出连接请求时引发的事件.
 *请注意,这与WebSocket会话建立不同,而是客户端在子协议中首次尝试连接,例如发送STOMP CONNECT帧.
 * @author: mayijie
 * @date:   2017年9月24日 下午11:45:56   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Component
public class WebSocketConnectListener extends HospitalQueueWebSocketListener<SessionConnectEvent> implements ApplicationListener<SessionConnectEvent> {

	@Autowired
	private ApplicationContext cApplicationContext;
	/**
	 * 
	 * <p>Title: onApplicationEvent</p>   
	 * <p>Description: Event raised when a new WebSocket client using a Simple Messaging Protocol (e.g. STOMP) as the WebSocket sub-protocol issues a connect request.
     * Note that this is not the same as the WebSocket session getting established but rather the client's first attempt to connect within the sub-protocol, for example sending the STOMP CONNECT frame.</p>   
	 *当使用简单消息传递协议(例如STOMP)作为WebSocket子协议的新WebSocket客户端发出连接请求时引发的事件.
	 *请注意,这与WebSocket会话建立不同,而是客户端在子协议中首次尝试连接,例如发送STOMP CONNECT帧.
	 * @param event   
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(SessionConnectEvent event) {
		// TODO Auto-generated method stub
		try {
			super.analysisMessage(event, StompCommand.CONNECT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IHandleStompCommandSerivce obtainIHandleStompCommandSerivce(String stompCommandSerivceName)
			throws Exception {
		
		// TODO Auto-generated method stub
		return (IHandleStompCommandSerivce) cApplicationContext.getBean(stompCommandSerivceName);
	}

	@Override
	public IHandleStompCommandSerivce obtainIHandleStompCommandSerivce() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * org.springframework.web.socket.messaging.SubProtocolWebSocketHandler
     * 
     * 
     * org.springframework.web.socket.messaging.StompSubProtocolHandler handleMessageFromClient
     */
	
	
}
