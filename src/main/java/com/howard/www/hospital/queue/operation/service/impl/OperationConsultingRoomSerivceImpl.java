package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationConsultingRoomDao;
import com.howard.www.hospital.queue.operation.domain.ConsultingRoomEntity;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OperationConsultingRoomSerivceImpl implements IOperationConsultingRoomSerivce {
	private static final Logger logger = LoggerFactory.getLogger(OperationConsultingRoomSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;

	private ConcurrentHashMap<String, ConsultingRoomEntity> consultingRoomMap = new ConcurrentHashMap<String, ConsultingRoomEntity>();

	@Override
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainIOperationConsultingRoomDao()
				.obtainConsultingRoomInfo(queryParameters);
	}

	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		List<JSONObject> consultingRoomItems =obtainConsultingRoomInfo(queryParameters);
		/**
		 * 遍历构造诊室对象信息
		 */
		if(consultingRoomItems!=null&&consultingRoomItems.size()>0){
			analyticStructure(consultingRoomItems);
		}
	}

	@Override
	public ConsultingRoomEntity obtainConsultingRoomEnityByRoomCodeFromMap(String roomCode) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * consultingRoomMap的Key为room_code,
		 * value为ConsultingRoomEntity对象,
		 * 通过room_code找到ConsultingRoomEntity对象
		 */
		if("".equals(FrameworkStringUtils.asString(roomCode))){
			logger.info("obtainConsultingRoomEnityByRoomCodeFromMap方法传入roomCode参数为空");
			
		}else{
			return consultingRoomMap.get(roomCode);
		}
		return null;
	}

	private void analyticStructure(List<JSONObject> consultingRoomItems) throws Exception {
		if (consultingRoomItems != null && consultingRoomItems.size() > 0) {
			ConsultingRoomEntity consultingRoomEntity=null;
			for(JSONObject consultingRoomItem:consultingRoomItems){
				consultingRoomEntity=new ConsultingRoomEntity(consultingRoomItem);
				/**
				 * consultingRoomMap的Key为room_code,value为ConsultingRoomEntity对象
				 */
				pushEntityToConsultingRoomMap(consultingRoomEntity);
			}
		}
	}

	private void  pushEntityToConsultingRoomMap(ConsultingRoomEntity consultingRoomEntity)throws Exception{
		if(consultingRoomEntity!=null){
			String roomCode=FrameworkStringUtils.asString(consultingRoomEntity.getRoomCode());
			if(!"".equals(roomCode)){
				consultingRoomMap.put(roomCode, consultingRoomEntity);
				logger.info("将room_code为:"+roomCode+"的ConsultingRoomEntity对象放入到consultingRoomMap中");
			}
		}
	}
	
	private IOperationConsultingRoomDao obtainIOperationConsultingRoomDao() throws Exception {
		return (IOperationConsultingRoomDao) cApplicationContext.getBean("operationConsultingRoomDao");
	}

	@Override
	public boolean existConsultingRoomEnityByRoomCode(String roomCode) throws Exception {
		// TODO Auto-generated method stub
		if(obtainConsultingRoomEnityByRoomCodeFromMap(roomCode)!=null){
			logger.info("获取到roomCode为"+roomCode+"ConsultingRoomEntity对象");
			return true;
		}else{
			logger.info("没有获取到roomCode为"+roomCode+"ConsultingRoomEntity对象");
		}
		return false;
	}

	@Override
	public void structureSreenRoomContrast(String roomCode, String internetProtocol) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 添加诊室和屏设备的关系
		 */
		if(!"".equals(FrameworkStringUtils.asString(roomCode))
				&&
				!"".equals(FrameworkStringUtils.asString(internetProtocol))){
			ConsultingRoomEntity consultingRoomEntity=consultingRoomMap.get(roomCode);
			if(consultingRoomEntity!=null){
				if(obtainIOperationScreenDeviceService().existScreenDeviceByIP(internetProtocol)==true){
					consultingRoomEntity.pustScreenDeviceInternetProtocolToRoom(internetProtocol);
					logger.info("在"+roomCode+"对应的ConsultingRoomEntity上添加了InternetProtocol为"+internetProtocol+"ScreenDeviceEntity映射关系");
				}else{
					
				}
			}else{
				
			}
		}else{
			
		}
		
	}
	
	private IOperationScreenDeviceService obtainIOperationScreenDeviceService()throws Exception{
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}

	@Override
	public Vector<String> obtainCorrespondingScreenIpVectorInTheConsultingRoom(String roomCode) throws Exception {
		// TODO Auto-generated method stub
		ConsultingRoomEntity consultingRoomEntity=obtainConsultingRoomEnityByRoomCodeFromMap(roomCode);
		if(consultingRoomEntity!=null){
			return consultingRoomEntity.getInternetProtocolItems();
		}
		return null;
	}

	@Override
	public Vector<String> obtainCorrespondingScreenIdentityVectorInTheConsultingRoom(String roomCode) throws Exception {
		// TODO Auto-generated method stub
		ConsultingRoomEntity consultingRoomEntity=obtainConsultingRoomEnityByRoomCodeFromMap(roomCode);
		if(consultingRoomEntity!=null){
			return consultingRoomEntity.getScreenDeviceIdentityItems();
		}
		return null;
	}	
}
