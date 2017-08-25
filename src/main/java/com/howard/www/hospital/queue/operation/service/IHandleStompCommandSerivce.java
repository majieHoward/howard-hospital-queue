package com.howard.www.hospital.queue.operation.service;

import org.springframework.messaging.Message;

public interface IHandleStompCommandSerivce {
	public void executeStompCommand(Message<?> message) throws Exception;
}
