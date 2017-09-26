package com.howard.www.hospital.queue.operation.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;
@Component
public class WebSocketConnectedListener extends HospitalQueueWebSocketListener<SessionConnectedEvent>
		implements ApplicationListener<SessionConnectedEvent> {

	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			super.analysisMessage(event, StompCommand.CONNECTED);
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

}
