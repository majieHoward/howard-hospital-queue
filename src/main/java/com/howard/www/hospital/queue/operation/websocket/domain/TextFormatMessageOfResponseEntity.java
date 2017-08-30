package com.howard.www.hospital.queue.operation.websocket.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @ClassName:  TextFormatMessageOfResponseEntity   
 * @Description:TODO 向客户端发送文本格式的消息
 * @author: mayijie
 * @date:   2017年8月30日 上午12:04:42   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextFormatMessageOfResponseEntity extends MessageOfResponseEntity implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	//回复的消息内容
	private String content;
	@Override
	public String getMsgType() {
		this.setMsgType("text");
		// TODO Auto-generated method stub
		return super.getMsgType();
	}
    	
	
}
