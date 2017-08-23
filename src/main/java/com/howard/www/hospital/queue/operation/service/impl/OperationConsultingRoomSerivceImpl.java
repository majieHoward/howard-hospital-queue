package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationConsultingRoomDao;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class OperationConsultingRoomSerivceImpl implements IOperationConsultingRoomSerivce{
	@Autowired
	private ApplicationContext cApplicationContext;
	
	@Override
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
	    List<JSONObject> consultingRoomItems=obtainIOperationConsultingRoomDao().obtainConsultingRoomInfo(queryParameters);
		System.out.println(FrameworkStringUtils.asString(consultingRoomItems));
	    return null;
	}

	private IOperationConsultingRoomDao obtainIOperationConsultingRoomDao()throws Exception{
		return (IOperationConsultingRoomDao) cApplicationContext.getBean("operationConsultingRoomDao");
	}

}
