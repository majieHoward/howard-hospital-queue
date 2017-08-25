package com.howard.www.hospital.queue.operation.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.ConsultingRoomEntity;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;

import net.sf.json.JSONArray;

public class OperationDoctorAttributeServiceImpl implements IOperationDoctorAttributeService {
	@Autowired
	private ApplicationContext cApplicationContext;

	private ConcurrentHashMap<String, ConsultingRoomEntity> consultingRoomItems = new ConcurrentHashMap<String, ConsultingRoomEntity>();
	@Override
	public JSONArray obtainDoctorAttributeInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
