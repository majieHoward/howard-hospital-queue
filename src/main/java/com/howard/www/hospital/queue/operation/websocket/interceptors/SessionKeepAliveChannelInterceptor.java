package com.howard.www.hospital.queue.operation.websocket.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
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
		//在消息实际发送到频道之前调用
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
		return super.preSend(message, channel);
	}

	/**
	 * 
	 * <p>Title: afterSendCompletion</p>   
	 * <p>Description: 在完成发送后调用,无论任何异常已被提出,从而允许正确的资源清理</p>   
	 * @param message
	 * @param channel
	 * @param sent
	 * @param ex   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#afterSendCompletion(org.springframework.messaging.Message, org.springframework.messaging.MessageChannel, boolean, java.lang.Exception)
	 */
	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		// TODO Auto-generated method stub
		//在完成发送后调用,无论任何异常已被提出,从而允许正确的资源清理
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
		super.afterSendCompletion(message, channel, sent, ex);
	}

	/**
	 * 
	 * <p>Title: postSend</p>   
	 * <p>Description:  在发送调用后立即调用</p>   
	 * @param message
	 * @param channel
	 * @param sent   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#postSend(org.springframework.messaging.Message, org.springframework.messaging.MessageChannel, boolean)
	 */
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
		super.postSend(message, channel, sent);
	}

	/**
	 * 
	 * <p>Title: preReceive</p>   
	 * <p>Description: 一旦接收到被调用并在实际检索到一个消息之前就调用它</p>   
	 * @param channel
	 * @return   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#preReceive(org.springframework.messaging.MessageChannel)
	 */
	@Override
	public boolean preReceive(MessageChannel channel) {
		// TODO Auto-generated method stub
	    /**
	     * 一旦接收到被调用并在实际检索到一个消息之前就调用它
	     */
		return super.preReceive(channel);
	}

	/**
	 * 
	 * <p>Title: postReceive</p>   
	 * <p>Description: 在消息已被检索之后,但在返回给调用者之前立即调用</p>   
	 * @param message
	 * @param channel
	 * @return   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#postReceive(org.springframework.messaging.Message, org.springframework.messaging.MessageChannel)
	 */
	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		// TODO Auto-generated method stub
		/**
		 * 在消息已被检索之后,但在返回给调用者之前立即调用
		 */
		return super.postReceive(message, channel);
	}

	/**
	 * 
	 * <p>Title: afterReceiveCompletion</p>   
	 * <p>Description: 在完成接收后调用,无论任何异常已被提出,从而允许正确的资源清理</p>   
	 * @param message
	 * @param channel
	 * @param ex   
	 * @see org.springframework.messaging.support.ChannelInterceptorAdapter#afterReceiveCompletion(org.springframework.messaging.Message, org.springframework.messaging.MessageChannel, java.lang.Exception)
	 */
	@Override
	public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
		// TODO Auto-generated method stub
		/**
		 * 在完成接收后调用,无论任何异常已被提升,从而允许正确的资源清理
		 */
		super.afterReceiveCompletion(message, channel, ex);
	}
}
