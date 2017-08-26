package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationScreenConsultingDao;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationScreenConsultingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OperationScreenConsultingServiceImpl implements IOperationScreenConsultingService {
	private static final Logger logger = LoggerFactory.getLogger(OperationScreenConsultingServiceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	
	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> screenConsultingItems=obtainScreenConsultingInfo(queryParameters);
		/**
		 * 遍历构造诊室对象信息
		 */
		if(screenConsultingItems!=null&&screenConsultingItems.size()>0){
			analyticStructure(screenConsultingItems);
		}
	}

	private void analyticStructure(List<JSONObject> screenConsultingItems)throws Exception{
		if (screenConsultingItems != null && screenConsultingItems.size() > 0) {
			for (JSONObject screenConsultingItem : screenConsultingItems) {
				structureSreenRoomContrast(screenConsultingItem);
			}
		}
	}
	
	private void structureSreenRoomContrast(JSONObject screenConsultingItem)throws Exception{
		if(screenConsultingItem!=null){
			String internetProtocol=FrameworkStringUtils.asString(screenConsultingItem.get("IP"));
			String roomCode=FrameworkStringUtils.asString(screenConsultingItem.get("RC"));
			if(!"".equals(internetProtocol)&&!"".equals(roomCode)){
				obtainIOperationConsultingRoomSerivce().structureSreenRoomContrast(roomCode, internetProtocol);
				obtainIOperationScreenDeviceService().structureSreenRoomContrast(roomCode, internetProtocol);
			}else{
				
			}
		}
	}
	
	@Override
	public JSONArray obtainScreenConsultingInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * SELECT
		 * ***sreen.internet_protocol AS IP,
		 * ***sreen.screen_device_identity AS SDI,
		 * ***sreen.serial_number AS SN,
		 * ***room.ROOM_CODE AS RC,
		 * ***room.ROOM_NAME AS RN,
		 * ***sreen.available AS SA,
		 * ***relation.available AS RA,
		 * ***sreen.screen_device_specification
		 * FROM
		 * ***hhq_screen_device AS sreen
		 * INNER JOIN 
		 * ***hhq_screen_consulting AS relation 
		 * ON sreen.screen_device_identity = relation.screen_device_identity
		 * INNER JOIN 
		 * ***room_dict AS room 
		 * ON room.ROOM_CODE = relation.room_code
		 */
		return obtainIOperationConsultingRoomDao().obtainScreenConsultingInfo(queryParameters);
	}

	private IOperationScreenConsultingDao obtainIOperationConsultingRoomDao() throws Exception{
		return (IOperationScreenConsultingDao) cApplicationContext.getBean("operationScreenConsultingDao");
	}
	
	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce()throws Exception{
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}
	
	private IOperationScreenDeviceService obtainIOperationScreenDeviceService()throws Exception{
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}
}
