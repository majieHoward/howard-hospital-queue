package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationDoctorAttributeDao;
import com.howard.www.hospital.queue.operation.domain.DoctorAttributeEntity;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OperationDoctorAttributeServiceImpl implements IOperationDoctorAttributeService {
	private static final Logger logger = LoggerFactory.getLogger(OperationDoctorAttributeServiceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	private ConcurrentHashMap<String, DoctorAttributeEntity> doctorAttributeMap = new ConcurrentHashMap<String, DoctorAttributeEntity>();

	@Override
	public JSONArray obtainDoctorAttributeInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainIOperationDoctorAttributeDao().obtainDoctorItemsAttribute(queryParameters);
	}

	private void analyticStructure(List<JSONObject> doctorAttributeItems) throws Exception {
		if (doctorAttributeItems != null && doctorAttributeItems.size() > 0) {
			DoctorAttributeEntity doctorAttributeEntity=null;
			for(JSONObject doctorAttributeItem:doctorAttributeItems){
				doctorAttributeEntity=new DoctorAttributeEntity(doctorAttributeItem);
				/**
				 * doctorAttributeMap的Key为doctorJobNumber,value为DoctorAttributeEntity对象
				 */
				pushEntityToDoctorAttributeMap(doctorAttributeEntity);
			}
		}
	}

	private void  pushEntityToDoctorAttributeMap(DoctorAttributeEntity doctorAttributeEntity)throws Exception{
		if(doctorAttributeEntity!=null){
			String doctorJobNumber=FrameworkStringUtils.asString(doctorAttributeEntity.getDoctorJobNumber());
			if(!"".equals(doctorJobNumber)){
				doctorAttributeMap.put(doctorJobNumber, doctorAttributeEntity);
				logger.info("将doctorJobNumber为:"+doctorJobNumber+"的DoctorAttributeEntity对象放入到doctorAttributeMap中");
			}
		}
	}
	
	private IOperationDoctorAttributeDao obtainIOperationDoctorAttributeDao() throws Exception {
		return (IOperationDoctorAttributeDao) cApplicationContext.getBean("operationDoctorAttributeDao");
	}

	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> doctorAttributeItems = obtainDoctorAttributeInfo(queryParameters);
		analyticStructure(doctorAttributeItems);
	}
}
