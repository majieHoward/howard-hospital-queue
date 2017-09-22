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
			    /**
			     * {
			     * ***"I":"5736",
			     * ***"N":"武平",
			     * ***"GN":"针灸康复科病区",
			     * ***"T":"主任医师",
			     * ***"DC":"1110201",
			     * ***"DR":null
			     * }
			     */
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

	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> doctorAttributeItems = obtainDoctorAttributeInfo(queryParameters);
		analyticStructure(doctorAttributeItems);
	}

	@Override
	public boolean existDoctorAttributeEntityByDoctorJobNumber(String doctorJobNumber) throws Exception {
		// TODO Auto-generated method stub
		if("".equals(FrameworkStringUtils.asString(doctorJobNumber))){
			throw new RuntimeException("40002");
			//throw new RuntimeException("缺少医生工号(doctorJobNumber)");
		}
		// TODO Auto-generated method stub
		if(obtainDoctorAttributeEntityByDoctorJobNumberFromMap(doctorJobNumber)!=null){
			logger.info("获取到doctorJobNumber为"+doctorJobNumber+"DoctorAttributeEntity对象");
			return true;
		}else{
			logger.info("没有获取到doctorJobNumber为"+doctorJobNumber+"DoctorAttributeEntity对象");
		}
		return false;
	}

	@Override
	public DoctorAttributeEntity obtainDoctorAttributeEntityByDoctorJobNumberFromMap(String doctorJobNumber)
			throws Exception {
		if("".equals(FrameworkStringUtils.asString(doctorJobNumber))){
			throw new RuntimeException("40003");
			//throw new RuntimeException("无效的医生工号(doctorJobNumber),请开发者检查doctorJobNumber的正确性，避免异常字符，注意大小写");
		}else{
			return this.doctorAttributeMap.get(doctorJobNumber);
		}
	}
	
	private IOperationDoctorAttributeDao obtainIOperationDoctorAttributeDao() throws Exception {
		return (IOperationDoctorAttributeDao) cApplicationContext.getBean("operationDoctorAttributeDao");
	}

	@Override
	public String obtainDoctorNameByDoctorJobNumberFromMap(String doctorJobNumber) throws Exception {
		// TODO Auto-generated method stub
		if(existDoctorAttributeEntityByDoctorJobNumber(doctorJobNumber)==true){
			return obtainDoctorAttributeEntityByDoctorJobNumberFromMap(doctorJobNumber).getDoctorName();
		}
		return null;
	}
}
