package com.howard.www.hospital.queue.operation.service;

import java.util.Vector;

public interface IOperationSubscribeMessageService {
	/**
	 * 
	 * @Title: sendAMessageToATopic   
	 * @Description: TODO 向订阅了某个主题的设备发送消息 
	 * @param: @param destination
	 * @param: @param messageBody
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public void sendAMessageToATopic(String destination, Object messageBody) throws Exception;
    
	public void sendAMessageToASreenDevice(String sreenDevice,String destination, Object messageBody)throws Exception;
	
    public void sendToCorrespondingScreenIdentityInConsulting(String destination,String roomCode,Object messageBody)throws Exception;

    public void sendToCorrespondingScreenIpInConsulting(String destination,String roomCode,Object messageBody)throws Exception;

    public void massMessageToCorrespondingScreenIdentityInConsulting(Vector<String> targetItems,String destination,Object messageBody)throws Exception;
}
