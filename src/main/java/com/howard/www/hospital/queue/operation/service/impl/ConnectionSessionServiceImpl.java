package com.howard.www.hospital.queue.operation.service.impl;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.howard.www.core.base.util.FrameworkStringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("connectionSessionService")
public class ConnectionSessionServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionSessionServiceImpl.class);
    /**
     * 保存上线的screenDevice和simpSessionId之间的对照关系
     */
	private ConcurrentHashMap<String, String> contrastRelation = new ConcurrentHashMap<String, String>();
	/**
	 * 保存所有的上线的screenDevice
	 */
	private Vector<String> screenDeviceItems = new Vector<String>();
    
	/**
	 * 
	 * @Title: evaluateContrastRelation   
	 * @Description: TODO 添加一组上线的screenDevice和simpSessionId之间的对照关系
	 * @param: @param simpSessionId
	 * @param: @param screenDevice
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public void evaluateContrastRelation(String simpSessionId, String screenDevice) throws Exception{
		
		if(contrastRelation==null||screenDeviceItems==null){
			throw new RuntimeException("contrastRelation or screenDeviceItems is null");
		}
		
		if (!"".equals(FrameworkStringUtils.asString(simpSessionId))
				&& !"".equals(FrameworkStringUtils.asString(screenDevice))) {
			if(contrastRelation.get(simpSessionId)==null){
				contrastRelation.put(simpSessionId, screenDevice);
			}
			/**根据room_code来寻找screenDivice**/
			if(!screenDeviceItems.contains(screenDevice)){
				screenDeviceItems.add(screenDevice);
			}
			
		}
		logger.info("execute CONNECT StompCommand");
		logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(contrastRelation)));
		logger.info(FrameworkStringUtils.asString(JSONArray.fromObject(screenDeviceItems)));
	}

	public void removeConnectionStatus(String simpSessionId) {
		if(!"".equals(FrameworkStringUtils.asString(simpSessionId))){
			/**
			 * 移除此向量中指定元素的第一个匹配项，如果向量不包含该元素，则元素保持不变
			 */
			screenDeviceItems.remove(contrastRelation.get(simpSessionId));
			contrastRelation.remove(simpSessionId);
			
		}
		logger.info("execute DISCONNECT StompCommand");
		logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(contrastRelation)));
		logger.info(FrameworkStringUtils.asString(JSONArray.fromObject(screenDeviceItems)));
	}
	
	/**
	 * 当获取一个room_code后需要寻找其对应的screenDevice设备是否在线
	 * 离线状态则收取不到推送消息
	 */
	


}
