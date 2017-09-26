package com.howard.www.hospital.queue.operation.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  HospitalQueueWebSocketListener   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年9月26日 下午9:26:11   
 *   
 * @param <T>  
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public abstract class HospitalQueueWebSocketListener<T extends AbstractSubProtocolEvent> {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketSubscribeListener.class);

	public void analysisMessage(T event, StompCommand stompCommand) throws Exception {

		if (event != null && stompCommand != null && true == judgeStompCommand(stompCommand)) {
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
			if (headerAccessor != null && stompCommand.equals(headerAccessor.getCommand())) {
				logger.info(FrameworkStringUtils.asString(stompCommand) + " : "
						+ FrameworkStringUtils.asString(JSONObject.fromObject(headerAccessor.getMessageHeaders())));
				StringBuffer stompCommandSerivceName = new StringBuffer();
				stompCommandSerivceName.append("stompCommand");
				stompCommandSerivceName.append(stompCommand);
				stompCommandSerivceName.append("Serivce");
				obtainIHandleStompCommandSerivce(stompCommandSerivceName.toString())
						.executeStompCommand(event.getMessage());
				stompCommandSerivceName.setLength(0);
			}
		}
	}

	public boolean judgeStompCommand(StompCommand parameterOfStompCommand) {

		for (StompCommand stompCommand : StompCommand.values()) {
			if (stompCommand.equals(parameterOfStompCommand)) {
				return true;
			}
		}
		return false;
	}

	public abstract IHandleStompCommandSerivce obtainIHandleStompCommandSerivce(String stompCommandSerivceName)
			throws Exception;

	public abstract IHandleStompCommandSerivce obtainIHandleStompCommandSerivce() throws Exception;
}
