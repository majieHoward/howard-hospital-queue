package com.howard.www.hospital.queue.operation.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;


@Component
public class WebSocketSubscribeListener extends HospitalQueueWebSocketListener<SessionSubscribeEvent> implements ApplicationListener<SessionSubscribeEvent> {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketSubscribeListener.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public void onApplicationEvent(SessionSubscribeEvent event) {
		// TODO Auto-generated method stub
		
	    /**
	     * {
	     * ***"simpMessageType": "SUBSCRIBE",
	     * ***"stompCommand": "SUBSCRIBE",
	     * ***"nativeHeaders": {
	     * ******"id": [
	     * *********"sub-1"
	     * ******],
	     * ******"destination": [
	     * *********"/screenDevice/171.19.231.2/subscribe"
	     * ******]
	     * ***},
	     * ***"simpSessionAttributes": {},
	     * ***"simpHeartbeat": [
	     * *********0,
	     * *********0
	     * ***],
	     * ***"simpSubscriptionId": "sub-1",
	     * ***"simpSessionId": "fokhk0_s",
	     * ***"simpDestination": "/screenDevice/171.19.231.2/subscribe"
	     * }
	     */
		try {
			super.analysisMessage(event, StompCommand.SUBSCRIBE);
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
