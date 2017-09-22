package com.howard.www.hospital.queue.operation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Repository;

import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

@Repository("stompCommandACKSerivce")
public class HandleACKStompCommandSerivceImpl implements IHandleStompCommandSerivce {
	private static final Logger logger = LoggerFactory.getLogger(HandleACKStompCommandSerivceImpl.class);

	@Override
	public void executeStompCommand(Message<?> message) throws Exception {
		// TODO Auto-generated method stub
        /**
         * {
         * ***"headers":{
         * ******"simpMessageType":"OTHER",
         * ******"stompCommand":"ACK",
         * ******"nativeHeaders":{
         * *********"message-id":[
         * ************"05i6fxdr-1"
         * *********],
         * *********"subscription":[
         * ************"sub-0"
         * *********]
         * ******},
         * ******"simpSessionAttributes":{},
         * ******"simpHeartbeat":[
         * *********0,
         * *********0
         * ******],
         * ******"simpSessionId":"05i6fxdr"
         * ***},
         * ***"payload":[
         * ***]
         * }
         */
		 logger.info("message.getHeaders().getId():"+message.getHeaders().getId());
	}

}
