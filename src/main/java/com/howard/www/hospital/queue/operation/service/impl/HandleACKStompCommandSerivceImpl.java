package com.howard.www.hospital.queue.operation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Repository;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  HandleACKStompCommandSerivceImpl   
 * @Description:TODO Socket ack是指当socket接收到数据之后,发送一个ack字符串(比如$ACK)给socket发送方.
 * 这样,socket发送方可以根据是否收到了ack判断对方是否收到了数据.Socket ack是显示的在应用程序中加入的一种通讯协议.
 * 如果不使用ack,在socket通讯中,可能会丢失数据   
 * @author: mayijie
 * @date:   2017年9月23日 上午2:57:31   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("stompCommandACKSerivce")
public class HandleACKStompCommandSerivceImpl implements IHandleStompCommandSerivce {
	private static final Logger logger = LoggerFactory.getLogger(HandleACKStompCommandSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	
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
		 logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(message)));
		 
	}

}
