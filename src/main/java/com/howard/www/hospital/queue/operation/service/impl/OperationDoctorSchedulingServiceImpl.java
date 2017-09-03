package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationDoctorSchedulingDao;
import com.howard.www.hospital.queue.operation.domain.CurrentDoctorSchedulingEntity;
import com.howard.www.hospital.queue.operation.domain.DoctorAttributeEntity;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: OperationDoctorSchedulingServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mayijie
 * @date: 2017年8月31日 下午4:17:34
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class OperationDoctorSchedulingServiceImpl implements IOperationDoctorSchedulingService {

	private static final Logger logger = LoggerFactory.getLogger(OperationDoctorSchedulingServiceImpl.class);
	/**
	 * doctorSchedulingMap key为ROOM_CODE
	 */
	private ConcurrentHashMap<String, ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>> doctorSchedulingMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>>();
	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public JSONArray obtainCurrentDoctorSchedulingInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainIOperationDoctorSchedulingDao().obtainCurrentDoctorSchedulingInfo(queryParameters);
	}

	private IOperationDoctorSchedulingDao obtainIOperationDoctorSchedulingDao() throws Exception {
		return (IOperationDoctorSchedulingDao) cApplicationContext.getBean("operationDoctorSchedulingDao");
	}

	private IOperationDoctorAttributeService obtainIOperationDoctorAttributeService() throws Exception {
		return (IOperationDoctorAttributeService) cApplicationContext.getBean("operationDoctorAttributeService");
	}

	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> doctorSchedulingItems = obtainCurrentDoctorSchedulingInfo(queryParameters);
		if (doctorSchedulingItems != null && doctorSchedulingItems.size() > 0) {
			analyticStructure(doctorSchedulingItems);
		}
	}

	private void analyticStructure(List<JSONObject> doctorSchedulingItems) throws Exception {
		if (doctorSchedulingItems != null && doctorSchedulingItems.size() > 0) {
			CurrentDoctorSchedulingEntity currentDoctorSchedulingEntity = null;
			for (JSONObject doctorSchedulingItem : doctorSchedulingItems) {
				/**
				 * {
				 * ***"D":"4020",
				 * ***"RC":"198",
				 * ***"VTD":"下午",
				 * ***"VD":"20170830000000"
				 * }
				 */
				currentDoctorSchedulingEntity = new CurrentDoctorSchedulingEntity(doctorSchedulingItem);
				/**
				 * consultingRoomMap的Key为room_code,value为ConsultingRoomEntity对象
				 */
				pushEntityToDoctorSchedulingMap(currentDoctorSchedulingEntity);
			}
		}
	}

	private void pushEntityToDoctorSchedulingMap(CurrentDoctorSchedulingEntity currentDoctorSchedulingEntity)
			throws Exception {
		if (currentDoctorSchedulingEntity != null) {
			String roomCode = FrameworkStringUtils.asString(currentDoctorSchedulingEntity.getRoomCode());
			String visitTimeDescCode = FrameworkStringUtils.asString(currentDoctorSchedulingEntity.getVisitTimeDescCode());
			/**
			 * schedulingMap key为visitTimeDescCode
			 */
			ConcurrentHashMap<String, CurrentDoctorSchedulingEntity> schedulingMap;
			if (!"".equals(roomCode)) {
				schedulingMap = obtainTimeIntervalSchedulingByRoomCode(roomCode);
				if (schedulingMap == null) {
					schedulingMap = new ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>();
					/**
					 * 将roomCode为:204的ConcurrentHashMap<String, ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>>对象放入到doctorSchedulingMap中
					 */
					doctorSchedulingMap.put(roomCode, schedulingMap);
					logger.info("将roomCode为:" + roomCode
							+ "的ConcurrentHashMap<String, ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>>对象放入到doctorSchedulingMap中");
				}
				/**
				 * 将visitTimeDescCode为:1的ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>对象放入到schedulingMap中
				 */
				schedulingMap.put(visitTimeDescCode, currentDoctorSchedulingEntity);
				logger.info("将visitTimeDescCode为:" + visitTimeDescCode
						+ "的ConcurrentHashMap<String, CurrentDoctorSchedulingEntity>对象放入到schedulingMap中");
			}
		}
	}

	@Override
	public ConcurrentHashMap<String, CurrentDoctorSchedulingEntity> obtainTimeIntervalSchedulingByRoomCode(
			String roomCode) throws Exception {
		if ("".equals(FrameworkStringUtils.asString(roomCode))) {
			throw new RuntimeException("");
		} else {
			return doctorSchedulingMap.get(FrameworkStringUtils.asString(roomCode));
		}
	}

	@Override
	public CurrentDoctorSchedulingEntity obtainAtCertainTimesSchedulingByRoomCodeAndTime(String roomCode, String time)
			throws Exception {
		// TODO Auto-generated method stub、
		if ("".equals(FrameworkStringUtils.asString(roomCode)) || "".equals(FrameworkStringUtils.asString(time))) {
			throw new RuntimeException("");
		} else {
			ConcurrentHashMap<String, CurrentDoctorSchedulingEntity> schedulingMap = obtainTimeIntervalSchedulingByRoomCode(
					roomCode);
			if (schedulingMap != null) {
				return schedulingMap.get(time);
			}
		}
		return null;
	}

	@Override
	public DoctorAttributeEntity obtainAtCertainTimesDoctorByRoomCodeAndTime(String roomCode, String time)
			throws Exception {
		if("".equals(roomCode)||"".equals(time)){
		    	throw new RuntimeException("");
		}
		// TODO Auto-generated method stub
		CurrentDoctorSchedulingEntity currentDoctorSchedulingEntity = obtainAtCertainTimesSchedulingByRoomCodeAndTime(
				roomCode, time);
		if (currentDoctorSchedulingEntity != null) {
			if (!"".equals(FrameworkStringUtils.asString(currentDoctorSchedulingEntity.getDoctorJobNumber()))) {
				return obtainIOperationDoctorAttributeService().obtainDoctorAttributeEntityByDoctorJobNumberFromMap(
						FrameworkStringUtils.asString(currentDoctorSchedulingEntity.getDoctorJobNumber()));
			}

		}
		return null;
	}

	@Override
	public JSONObject obtainAtCertainTimesDoctor(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		String roomCode=FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("roomCode"));
	    String time=FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("time"));
	    if("".equals(roomCode)||"".equals(time)){
	    	throw new RuntimeException("");
	    }
	    return obtainAtCertainTimesDoctor(roomCode,time);
	}

	@Override
	public JSONObject obtainAtCertainTimesDoctor(String roomCode, String time) throws Exception {
		// TODO Auto-generated method stub
		if("".equals(roomCode)||"".equals(time)){
	    	throw new RuntimeException("");
	    }
	    return JSONObject.fromObject(obtainAtCertainTimesDoctorByRoomCodeAndTime(roomCode,time));
	}

}
