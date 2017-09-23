package com.howard.www.hospital.queue.operation.websocket.interceptors;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.howard.www.core.base.util.FrameworkStringUtils;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  HttpSessionIdHandshakeInterceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年8月23日 下午11:00:14   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HttpSessionIdHandshakeInterceptor implements HandshakeInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(HttpSessionIdHandshakeInterceptor.class);

	/**
	 * 
	 * <p>Title: beforeHandshake</p>   
	 * <p>Description: 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性</p>   
	 * @param request
	 * @param response
	 * @param wsHandler
	 * @param attributes
	 * @return
	 * @throws Exception   
	 * @see org.springframework.web.socket.server.HandshakeInterceptor#beforeHandshake(org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse, org.springframework.web.socket.WebSocketHandler, java.util.Map)
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		logger.info("client 使用websocket execute 握手拦截器");
		logger.info("在握手之前执行该方法, 继续握手返回true, 中断握手返回false. "
				+ "通过attributes参数设置WebSocketSession的属性");
		
		return true;
	}
    /**
     * 
     * <p>Title: afterHandshake</p>   
     * <p>Description: 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.</p>   
     * @param request
     * @param response
     * @param wsHandler
     * @param exception   
     * @see org.springframework.web.socket.server.HandshakeInterceptor#afterHandshake(org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse, org.springframework.web.socket.WebSocketHandler, java.lang.Exception)
     */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		logger.info("在握手之后执行该方法."
				+ " 无论是否握手成功都指明了响应状态码和相应头.");
	}
}
