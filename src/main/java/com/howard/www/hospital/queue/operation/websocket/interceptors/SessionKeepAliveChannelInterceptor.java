package com.howard.www.hospital.queue.operation.websocket.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IHandleStompCommandSerivce;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: SessionKeepAliveChannelInterceptor
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mayijie
 * @date: 2017年8月23日 下午11:00:21
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class SessionKeepAliveChannelInterceptor extends ChannelInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionKeepAliveChannelInterceptor.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	/**
	 * 
	 * <p>Title: preSend</p>   
	 * <p>Description: 在消息实际发送到频道之前调用.如果需要,这允许修改消息.
	 * 如果此方法返回,null则不会发生实际的发送调用</p>   
	 * @param message
	 * @param channel
	 * @return   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#preSend(org.springframework.messaging.Message, org.springframework.messaging.MessageChannel)
	 */
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
        /**
         * {
         * ***"headers":{
         * ******"simpMessageType":"CONNECT",
         * ******"stompCommand":"CONNECT",
         * ******"nativeHeaders":{
         * *********"screenDevice":["SD987JH"],
         * *********"accept-version":["1.1,1.0"],
         * *********"heart-beat":["10000,10000"]
         * ******},
         * ******"simpSessionAttributes":{},
         * ******"simpHeartbeat":[10000,10000],
         * ******"simpSessionId":"33bkkacb"
         * ***},
         * ***"payload":[]
         * }
         */
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

		/**
		 * 获取
		 */
		if (StompCommand.CONNECT.equals(accessor.getCommand())) {
			logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(message)));
			String screenDevice = accessor.getFirstNativeHeader("screenDevice");
			logger.info("obtain connected screenDevice identification is " + screenDevice);
		}
		try {
			analysisMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.preSend(message, channel);
	}

	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		// TODO Auto-generated method stub
        /**
         * {
         * ***"headers":{
         * ******"simpMessageType":"DISCONNECT",
         * ******"stompCommand":"DISCONNECT",
         * ******"simpSessionAttributes":{
         * ******},
         * ******"simpHeartbeat":[0,0],
         * ******"simpSessionId":"33bkkacb"
         * ***},
         * ***"payload":[]
         * }
         */
		StompHeaderAccessor accessor =StompHeaderAccessor.wrap(message);
		StompCommand command =accessor.getCommand();
		if(StompCommand.DISCONNECT.equals(command)){
		   logger.info("断开连接");
		   logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(message)));
		}
		try {
			analysisMessage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.afterSendCompletion(message, channel, sent, ex);
	}

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub
        /**
         * {
         * ***"headers":{
         * ******"simpMessageType":"SUBSCRIBE",
         * ******"stompCommand":"SUBSCRIBE",
         * ******"nativeHeaders":{
         * *********"id":["sub-0"],
         * *********"destination":["/callTheName/public"]
         * ******},
         * ******"simpSessionAttributes":{},
         * ******"simpHeartbeat":[0,0],
         * ******"simpSubscriptionId":"sub-0",
         * ******"simpSessionId":"33bkkacb",
         * ******"simpDestination":"/callTheName/public"
         * ***},
         * ***"payload":[]
         * }
         */
		//.ack()
		/**
		 * {
		 * ***"headers":{
		 * ******"simpMessageType":"OTHER",
		 * ******"stompCommand":"ACK",
		 * ******"nativeHeaders":{
		 * *********"message-id":["33bkkacb-0"],
		 * *********"subscription":["sub-0"]
		 * ******},
		 * ******"simpSessionAttributes":{},
		 * ******"simpHeartbeat":[0,0],
		 * ******"simpSessionId":"33bkkacb"
		 * ***},
		 * ***"payload":[]
		 * }
		 */
		/**
		 * {
		 * ***"headers":{
		 * ******"simpMessageType":"DISCONNECT",
		 * ******"stompCommand":"DISCONNECT",
		 * ******"simpSessionAttributes":{
		 * ******},
		 * ******"simpSessionId":"33bkkacb"
		 * ***},
		 * ***"payload":[]
		 * }
		 */
		/**
		 * {
		 * ***"headers":{
		 * ******"simpMessageType":"MESSAGE",
		 * ******"stompCommand":"SEND",
		 * ******"nativeHeaders":{
		 * *********"destination":["/app/messageForwarding.websocket"],
		 * *********"content-length":["71"]
		 * ******},
		 * ******"simpSessionAttributes":{},
		 * ******"simpHeartbeat":[0,0],
		 * ******"simpSessionId":"odxi4gxx"
		 * ******,"simpDestination":"/app/messageForwarding.websocket"
		 * ***},
		 * ***"payload":[]
		 * }
		 */
		logger.info("在发送调用后立即调用。");
		logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(message)));
		super.postSend(message, channel, sent);
	}

	@Override
	public boolean preReceive(MessageChannel channel) {
		// TODO Auto-generated method stub
	    logger.info("在消息已被检索之后，但在返回给调用者之前立即调用.");
		return super.preReceive(channel);
	}

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		// TODO Auto-generated method stub
		logger.info("在消息已被检索之后，但在返回给调用者之前立即调用");
		return super.postReceive(message, channel);
	}

	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		// TODO Auto-generated method stub
		logger.info("在完成接收后调用，无论任何异常已被提升，从而允许正确的资源清理。");
		super.afterReceiveCompletion(message, channel, ex);
	}
	
	private void analysisMessage(Message<?> message)throws Exception{
		String stompCommand=FrameworkStringUtils.asString(message.getHeaders().get("stompCommand"));
		if(!"".equals(stompCommand)){
			StringBuffer stompCommandSerivceName=new StringBuffer();
			stompCommandSerivceName.append("stompCommand");
			stompCommandSerivceName.append(stompCommand);
			stompCommandSerivceName.append("Serivce");
			IHandleStompCommandSerivce stompCommandSerivce=(IHandleStompCommandSerivce) cApplicationContext.getBean(stompCommandSerivceName.toString());
			stompCommandSerivceName.setLength(0);
			stompCommandSerivce.executeStompCommand(message);
		}
	}
}
