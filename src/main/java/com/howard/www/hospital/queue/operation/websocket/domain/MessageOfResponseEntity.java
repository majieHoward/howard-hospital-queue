package com.howard.www.hospital.queue.operation.websocket.domain;

import com.howard.www.core.base.util.FrameworkStringUtils;
import lombok.Data;

/**
 * 
 * @ClassName: MessageOfResponseEntity
 * @Description:TODO(推送的)
 * @author: mayijie
 * @date: 2017年8月21日 下午4:13:45
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
public class MessageOfResponseEntity {
	// 接收方帐号(收到的OpenID)
	private String toUserName;
	// 开发者号
	private String fromUserName = FrameworkStringUtils.asString("HospitalQueueSystem");
	// 消息创建时间 (整型)
	private String createTime = FrameworkStringUtils.asString(System.currentTimeMillis());
	//
	private String msgType;

}
