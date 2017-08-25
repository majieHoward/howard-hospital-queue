package com.howard.www.hospital.queue.operation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Repository;

import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;
@Repository("stompCommandSUBSCRIBESerivce")
public class HandleSUBSCRIBEStompCommandSerivceImpl implements IHandleStompCommandSerivce {
	private static final Logger logger = LoggerFactory.getLogger(HandleSUBSCRIBEStompCommandSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	@Override
	public void executeStompCommand(Message<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}
    /**
     * 
     */
}
