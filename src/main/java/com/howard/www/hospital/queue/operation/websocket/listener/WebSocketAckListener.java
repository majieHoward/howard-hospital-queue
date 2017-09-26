package com.howard.www.hospital.queue.operation.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;
import com.howard.www.hospital.queue.operation.websocket.socket.messaging.SessionACKEvent;

/**
 * 
 * @ClassName:  WebSocketAckListener   
 * @Description:TODO    
 * @author: mayijie
 * @date:   2017年9月26日 下午8:57:08   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Component
public class WebSocketAckListener extends HospitalQueueWebSocketListener<SessionACKEvent> implements ApplicationListener<SessionACKEvent> {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketAckListener.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public void onApplicationEvent(SessionACKEvent event) {
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
			super.analysisMessage(event, StompCommand.ACK);
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
