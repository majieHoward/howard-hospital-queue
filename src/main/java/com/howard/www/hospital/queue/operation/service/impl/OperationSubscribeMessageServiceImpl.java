package com.howard.www.hospital.queue.operation.service.impl;

import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService;

public class OperationSubscribeMessageServiceImpl implements IOperationSubscribeMessageService {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ApplicationContext cApplicationContext;

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
			messagingTemplate.convertAndSendToUser(sreenDevice, destination, messageBody);
		}
	}
	
	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce() throws Exception {
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}

	
}
