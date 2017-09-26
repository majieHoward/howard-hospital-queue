package com.howard.www.hospital.queue.operation.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService;

/**
 * 
 * @ClassName:  OperationSubscribeMessageServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年9月24日 下午10:55:02   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class OperationSubscribeMessageServiceImpl implements IOperationSubscribeMessageService {

	private static final Logger logger = LoggerFactory.getLogger(OperationSubscribeMessageServiceImpl.class);
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ApplicationContext cApplicationContext;
	
	private IdGenerator defaultIdGenerator = new AlternativeJdkIdGenerator();
	
	private IdGenerator idGenerator = null;

	/**
	 * 
	 * <p>Title: sendAMessageToATopic</p>   
	 * <p>Description: 群发一个主题消息</p>   
	 * @param destination
	 * @param messageBody
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService#sendAMessageToATopic(java.lang.String, java.lang.Object)
	 */
	@Override
	public void sendAMessageToATopic(String destination, Object messageBody) throws Exception {
		// TODO Auto-generated method stub
		messagingTemplate.convertAndSend(destination, messageBody);
	}

	@Override
	public void sendToCorrespondingScreenIdentityInConsulting(String destination, String roomCode, Object messageBody) throws Exception {
		// TODO Auto-generated method stub
		if("".equals(FrameworkStringUtils.asString(roomCode))
				||"".equals(FrameworkStringUtils.asString(destination))){
			throw new RuntimeException("");
		}
		Vector<String> identityItems=obtainIOperationConsultingRoomSerivce().obtainCorrespondingScreenIdentityVectorInTheConsultingRoom(roomCode);
		massMessageToCorrespondingScreenIdentityInConsulting(identityItems,destination,messageBody);
	}

	@Override
	public void sendToCorrespondingScreenIpInConsulting(String destination,String roomCode, Object messageBody) throws Exception {
		// TODO Auto-generated method stub
		Vector<String> ipItems=obtainIOperationConsultingRoomSerivce().obtainCorrespondingScreenIpVectorInTheConsultingRoom(roomCode);
		if(ipItems==null||ipItems.size()<=0){
			/**
			 * 没有找到对应播放语音的屏幕设备
			 */
		}
		massMessageToCorrespondingScreenIdentityInConsulting(ipItems,destination,messageBody);
	}

	/**
	 * 
	 * <p>Title: massMessageToCorrespondingScreenIdentityInConsulting</p>   
	 * <p>Description: 给用户群逐一发送消息</p>   
	 * @param targetItems
	 * @param destination
	 * @param messageBody
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService#massMessageToCorrespondingScreenIdentityInConsulting(java.util.Vector, java.lang.String, java.lang.Object)
	 */
	@Override
	public void massMessageToCorrespondingScreenIdentityInConsulting(Vector<String> targetItems,String destination,Object messageBody) throws Exception {
		// TODO Auto-generated method stub
		if(targetItems!=null
				&&targetItems.size()>0
				&&!"".equals(FrameworkStringUtils.asString(destination))){
			for(String targetItem:targetItems){
				sendAMessageToASreenDevice(targetItem,destination, messageBody);
			}
		}else{
			
		}
	}
	/**
	 * 
	 * <p>Title: sendAMessageToASreenDevice</p>   
	 * <p>Description: 给指定用户发送消息</p>   
	 * @param sreenDevice
	 * @param destination
	 * @param messageBody
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService#sendAMessageToASreenDevice(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void sendAMessageToASreenDevice(String sreenDevice, String destination, Object messageBody)
			throws Exception {
		// TODO Auto-generated method stub
		if("".equals(FrameworkStringUtils.asString(sreenDevice))
				||"".equals(FrameworkStringUtils.asString(destination))){
			throw new RuntimeException("");
		}else{
			Map<String, Object> headers=new HashMap<String,Object>();
			UUID uuid=getIdGenerator().generateId();
			logger.info("convertAndSendToUser UUID :"+uuid.toString());
			headers.put("id", uuid);
			messagingTemplate.convertAndSendToUser(sreenDevice, destination, messageBody,headers);
		}
	}
	
	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce() throws Exception {
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}

	private IdGenerator getIdGenerator() {
		return (idGenerator != null ? idGenerator : defaultIdGenerator);
	}
	
}
