package com.howard.www.hospital.queue.operation.domain;

import java.io.Serializable;
import java.util.Vector;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: ConsultingRoomEntity
 * @Description:TODO(诊室详情信息放入到redis或cache中)
 * @author: mayijie
 * @date: 2017年8月21日 上午11:16:51
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultingRoomEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	// eg.儿科1诊断室
	private String designation;
	// eg.1楼
	private String floorNumber;
	// eg.300
	private String roomCode;
	// eg.儿科1诊断室（1楼）
	private String roomName;
	// eg.11201
	private String deptCode;

	private String roomType;

	private String simpleName;
	// eg.EKZDSDLD
	private String inputCode;
	// eg.171.217.95.14 (诊室对应的屏幕信息)
	private ScreenDeviceEntity screenDevice;
	// eg.171.217.95.14(作为KEY)
	private String internetProtocol;
	// eg.351521004992889(作为KEY)
	private String screenDeviceIdentity;
	// 是否可用'10A' '10X'
	private String available;

	private Vector<ScreenDeviceEntity> screenDeviceItems;

	private Vector<String> screenDeviceIdentityItems;

	public void structureConsultingRoomEntityFromJSON(JSONObject consultingRoomObject) throws Exception {
		if(consultingRoomObject==null){
			throw new RuntimeException("structureConsultingRoomEntityFromJSON方法传入的JSONObject对象为空");
		}
		/**
		 * select
		 * ***room.ROOM_CODE AS RC,
		 * ***room.ROOM_NAME AS RN,
		 * ***room.DEPT_CODE AS DC,
		 * ***room.ROOM_TYPE AS RT,
		 * ***room.SIMPLE_NAME AS SN,
		 * ***room.INPUT_CODE AS IC
		 * ***from room_dict room 
		 */
		this.setRoomCode(FrameworkStringUtils.asString(consultingRoomObject.get("RC")));
		this.setRoomName(FrameworkStringUtils.asString(consultingRoomObject.get("RN")));
		this.setDeptCode(FrameworkStringUtils.asString(consultingRoomObject.get("DC")));
		this.setRoomType(FrameworkStringUtils.asString(consultingRoomObject.get("RT")));
		this.setSimpleName(FrameworkStringUtils.asString(consultingRoomObject.get("SN")));
		this.setInputCode(FrameworkStringUtils.asString(consultingRoomObject.get("IC")));
	}
}
