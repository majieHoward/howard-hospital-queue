package com.howard.www.hospital.queue.operation.websocket.socket.messaging;

import java.security.Principal;

import org.springframework.messaging.Message;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
/**
 * 
 * @ClassName:  SessionACKEvent   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年9月26日 下午8:46:42   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@SuppressWarnings("serial")
public class SessionACKEvent extends AbstractSubProtocolEvent {

	public SessionACKEvent(Object source, Message<byte[]> message) {
		super(source, message);
		// TODO Auto-generated constructor stub
	}
	public SessionACKEvent(Object source, Message<byte[]> message, Principal user) {
		super(source, message, user);
		// TODO Auto-generated constructor stub
	}

	

}
