package com.howard.www.hospital.queue.operation.websocket.domain;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @ClassName: TextFormatMessageOfResponseEntity
 * @Description:TODO 向客户端发送文本格式的消息
 * @author: mayijie
 * @date: 2017年8月30日 上午12:04:42
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
public class CallAPatientMessageOfResponseEntity extends MessageOfResponseEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private String doctorJobNumber;

	private String roomCode;

	private String patientName;

	private String patientCode;

	public CallAPatientMessageOfResponseEntity(ConcurrentHashMap<String, Object> requiredParameter) {
		this.setDoctorJobNumber(FrameworkStringUtils.asString(requiredParameter.get("patientName")));
		this.setRoomCode(FrameworkStringUtils.asString(requiredParameter.get("roomCode")));
		this.setPatientName(FrameworkStringUtils.asString(requiredParameter.get("patientName")));
		this.setPatientCode(FrameworkStringUtils.asString(requiredParameter.get("patientCode")));
	}

	@Override
	public String getMsgType() {
		// TODO Auto-generated method stub
		return FrameworkStringUtils.asString("callAPatient");
	}
	
	
}
