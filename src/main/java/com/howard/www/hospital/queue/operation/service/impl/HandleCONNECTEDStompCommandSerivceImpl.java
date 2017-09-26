package com.howard.www.hospital.queue.operation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Repository;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

@Repository("stompCommandCONNECTEDSerivce")
public class HandleCONNECTEDStompCommandSerivceImpl implements IHandleStompCommandSerivce {
	private static final Logger logger = LoggerFactory.getLogger(HandleCONNECTEDStompCommandSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public void executeStompCommand(Message<?> message) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * {"headers":{"simpMessageType":"CONNECT","stompCommand":"CONNECT","nativeHeaders":{"screenDevice":["171.19.231.2"],"accept-version":["1.1,1.0"],"heart-beat":["10000,10000"]},"simpSessionAttributes":{},"simpHeartbeat":[10000,10000],"simpSessionId":"05i6fxdr"},"payload":[]}
		 */
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		String simpSessionId = FrameworkStringUtils.asString(accessor.getSessionId());
		String screenDevice = FrameworkStringUtils.asString(accessor.getFirstNativeHeader("screenDevice"));
		obtainConnectionSessionServiceImpl().evaluateContrastRelation(simpSessionId, screenDevice);
	}
	/**
	 * screenDevice 和 simpSessionId 建立对应的关系
	 * 
	 *
	 */
	
	private ConnectionSessionServiceImpl obtainConnectionSessionServiceImpl()throws Exception{
		return (ConnectionSessionServiceImpl) cApplicationContext.getBean("connectionSessionService");
	}
}
