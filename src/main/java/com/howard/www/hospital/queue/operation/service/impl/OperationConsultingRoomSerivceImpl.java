package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationConsultingRoomDao;
import com.howard.www.hospital.queue.operation.domain.ConsultingRoomEntity;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OperationConsultingRoomSerivceImpl implements IOperationConsultingRoomSerivce {
	@Autowired
	private ApplicationContext cApplicationContext;

	private ConcurrentHashMap<String, ConsultingRoomEntity> consultingRoomItems = new ConcurrentHashMap<String, ConsultingRoomEntity>();

	@Override
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> consultingRoomItems = obtainIOperationConsultingRoomDao()
				.obtainConsultingRoomInfo(queryParameters);
		/**
		 * 遍历构造诊室对象信息
		 */
		analyticStructure(consultingRoomItems);
		return null;
	}

	private void analyticStructure(List<JSONObject> consultingRoomItems) throws Exception {
		if (consultingRoomItems != null && consultingRoomItems.size() > 0) {

		}
	}

	private IOperationConsultingRoomDao obtainIOperationConsultingRoomDao() throws Exception {
		return (IOperationConsultingRoomDao) cApplicationContext.getBean("operationConsultingRoomDao");
	}

}
