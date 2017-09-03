package com.howard.www.hospital.queue.operation.websocket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.websocket.domain.MessageOfRequestEntity;

/**
 * 
 * @ClassName:  HospitalQueueWebsocketController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年8月23日 下午11:02:04   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Controller
public class HospitalQueueWebsocketController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/messageForwarding.websocket")
	public void messageForwarding(MessageOfRequestEntity entiy){
		messagingTemplate.convertAndSend("/callTheName/demo", FrameworkStringUtils.asString(entiy.getName())); 
	}
}
