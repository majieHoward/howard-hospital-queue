package com.howard.www.hospital.queue.operation.websocket.domain;
/**
 * 
 * @ClassName:  MessageOfRequestEntity   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年8月21日 下午4:14:05   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class MessageOfRequestEntity {
	/**
	 * 
	 */
	private String name;
	private String device;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
}
