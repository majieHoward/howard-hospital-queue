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

@Repository("stompCommandDISCONNECTSerivce")
public class HandleDISCONNECTStompCommandSerivceImpl implements IHandleStompCommandSerivce {
	private static final Logger logger = LoggerFactory.getLogger(HandleDISCONNECTStompCommandSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	@Override
	public void executeStompCommand(Message<?> message) throws Exception {
		// TODO Auto-generated method stub
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		String simpSessionId = FrameworkStringUtils.asString(accessor.getSessionId());
		obtainConnectionSessionServiceImpl().removeConnectionStatus(simpSessionId);
	}

	private ConnectionSessionServiceImpl obtainConnectionSessionServiceImpl()throws Exception{
		return (ConnectionSessionServiceImpl) cApplicationContext.getBean("connectionSessionService");
	}
}
