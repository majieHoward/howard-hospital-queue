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
/**
 * 
 * @ClassName:  OperationConsultingRoomSerivceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年9月4日 上午12:18:58   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class OperationConsultingRoomSerivceImpl implements IOperationConsultingRoomSerivce {
	private static final Logger logger = LoggerFactory.getLogger(OperationConsultingRoomSerivceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	/**
	 * consultingRoomMap的Key为room_code,value为ConsultingRoomEntity对象
	 */
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
			throw new RuntimeException("40004");
			//throw new RuntimeException("缺少诊断室编码(roomCode)");
		}else{
			return consultingRoomMap.get(roomCode);
		}
	}

	private void analyticStructure(List<JSONObject> consultingRoomItems) throws Exception {
		if (consultingRoomItems != null && consultingRoomItems.size() > 0) {
			ConsultingRoomEntity consultingRoomEntity=null;
			for(JSONObject consultingRoomItem:consultingRoomItems){
				/**
				 * {
				 * ***"RC":"275",
				 * ***"RN":"针灸康复科1诊断室（5楼）",
				 * ***"DC":"11101",
				 * ***"RT":null,
				 * ***"SN":null,
				 * ***"IC":"ZJKFKZDS"
				 * }
				 */
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
		if("".equals(FrameworkStringUtils.asString(roomCode))){
			throw new RuntimeException("40004");
			//throw new RuntimeException("缺少诊断室编码(roomCode)");
		}
		// TODO Auto-generated method stub
		if(obtainConsultingRoomEnityByRoomCodeFromMap(roomCode)!=null){
			logger.info("获取到roomCode为"+roomCode+"ConsultingRoomEntity对象");
			return true;
		}else{
			logger.info("没有获取到roomCode为"+roomCode+"ConsultingRoomEntity对象");
		}
		return false;
	}

	/**
	 * 
	 * <p>Title: structureSreenRoomContrast</p>   
	 * <p>Description: 在ROOM_CODE对应的ConsultingRoomEntity上添加(Vector push)InternetProtocol为IP的ScreenDeviceEntity映射关系</p>   
	 * @param roomCode
	 * @param internetProtocol
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce#structureSreenRoomContrast(java.lang.String, java.lang.String)
	 */
	@Override
	public void structureSreenRoomContrast(String roomCode, String internetProtocol) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 添加诊室和屏设备的关系
		 */
		/**
		 * {
		 * ***"IP":"171.19.231.4",
		 * ***"SDI":"351521004992890",
		 * ***"SN":"EQBAN7UCU4S8VK6D",
		 * ***"RC":"304","RN":"针灸康复科8诊断室（5楼）",
		 * ***"SA":"10A",
		 * ***"RA":"10A",
		 * ***"SDS":"A1578",
		 * ***"AI":null
		 * }
		 */
		if(!"".equals(FrameworkStringUtils.asString(roomCode))
				&&
				!"".equals(FrameworkStringUtils.asString(internetProtocol))){
			ConsultingRoomEntity consultingRoomEntity=consultingRoomMap.get(roomCode);
			if(consultingRoomEntity!=null){
				/**
				 * 在304对应的ConsultingRoomEntity上添加(Vector push)InternetProtocol为
				 * 171.19.231.4的ScreenDeviceEntity映射关系
				 */
				if(obtainIOperationScreenDeviceService().existScreenDeviceByIP(internetProtocol)==true){
					consultingRoomEntity.pustScreenDeviceInternetProtocolToRoom(internetProtocol);
					logger.info("在"+roomCode+"对应的ConsultingRoomEntity上添加(Vector push)InternetProtocol为"
					+internetProtocol+"的ScreenDeviceEntity映射关系");
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
