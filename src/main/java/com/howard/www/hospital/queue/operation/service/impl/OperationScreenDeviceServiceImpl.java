package com.howard.www.hospital.queue.operation.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationScreenDeviceDao;
import com.howard.www.hospital.queue.operation.domain.ScreenDeviceEntity;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  OperationScreenDeviceServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: mayijie
 * @date:   2017年9月4日 上午12:18:11   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class OperationScreenDeviceServiceImpl implements IOperationScreenDeviceService {

	private static final Logger logger = LoggerFactory.getLogger(OperationScreenDeviceServiceImpl.class);

	@Autowired
	private ApplicationContext cApplicationContext;
	/**
	 * screenDeviceMap key为屏幕设备的IP地址
	 */
	private ConcurrentHashMap<String, ScreenDeviceEntity> screenDeviceMap = new ConcurrentHashMap<String, ScreenDeviceEntity>();

	@Override
	public void initializingServiceBaseData(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		List<JSONObject> screenDeviceItems = obtainScreenDeviceInfo(queryParameters);
		/**
		 * 遍历构造诊室对象信息
		 */
		if (screenDeviceItems != null && screenDeviceItems.size() > 0) {
			analyticStructure(screenDeviceItems);
		}
	}

	@Override
	public JSONArray obtainScreenDeviceInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainIOperationScreenDeviceDao().obtainScreenDeviceInfo(queryParameters);
	}

	private void analyticStructure(List<JSONObject> screenDeviceItems) throws Exception {
		if (screenDeviceItems != null && screenDeviceItems.size() > 0) {
			ScreenDeviceEntity consultingRoomEntity = null;
			for (JSONObject screenDeviceItem : screenDeviceItems) {
				/**
				 * {
				 * ***"IP":"171.19.231.2",
				 * ***"SDI":"351521004992889",
				 * ***"SN":"BAHBBDB652502811",
				 * ***"DA":"10A",
				 * ***"SDS":"A1524",
				 * ***"AI":null,
				 * ***"SSE":"A1524",
				 * ***"PI":"P1932",
				 * ***"SSD":"风湿免疫科门诊屏",
				 * ***"SA":"10A",
				 * ***"PN":"interrogationSpot.P1932",
				 * ***"PS":"编号为P1932诊区外大屏展示页面模板",
				 * ***"PUA":"/hospital/queue/operation/interrogation/spot.P1932.exhibition",
				 * ***"PA":"10A"
				 * }
				 */
				consultingRoomEntity = new ScreenDeviceEntity(screenDeviceItem);
				/**
				 * consultingRoomMap的Key为room_code,value为ConsultingRoomEntity对象
				 */
				pushEntityToScreenDeviceMap(consultingRoomEntity);
			}
		}
	}

	private void pushEntityToScreenDeviceMap(ScreenDeviceEntity screenDeviceEntity) throws Exception {
		if (screenDeviceEntity != null) {
			String internetProtocol = FrameworkStringUtils.asString(screenDeviceEntity.getInternetProtocol());
			if (!"".equals(internetProtocol)) {
				screenDeviceMap.put(internetProtocol, screenDeviceEntity);
				logger.info("将IP为:" + internetProtocol + "的ScreenDeviceEntity对象放入到screenDeviceMap中");
			}
		}
	}

	@Override
	public ScreenDeviceEntity obtainScreenDeviceByIPFromMap(String internetProtocol) throws Exception {
		if("".equals(FrameworkStringUtils.asString(internetProtocol))){
			logger.info("obtainScreenDeviceByIPFromMap方法传入internetProtocol参数为空");
		}else{
			return screenDeviceMap.get(internetProtocol);
		}
		return null;
	}

	@Override
	public boolean existScreenDeviceByIP(String internetProtocol) throws Exception {
		// TODO Auto-generated method stub
		if(obtainScreenDeviceByIPFromMap(internetProtocol)!=null){
			logger.info("获取到internetProtocol为"+internetProtocol+"ScreenDeviceEntity对象");
			return true;
		}else{
			logger.info("没有获取到internetProtocol为"+internetProtocol+"ScreenDeviceEntity对象");
			return false;
		}
		
	}
	
	@Override
	public JSONObject obtainHomeScreenDisplayPage(IDataTransferObject queryParameters) throws Exception {
		if(queryParameters!=null){
			return JSONObject.fromObject(obtainScreenDeviceByIPFromMap(
					FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("internetProtocol"))));
		}
		return null;
	}
	
	@Override
	public ScreenDeviceEntity obtainScreenDeviceByIdentityFromMap(String screenDeviceIdentity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private IOperationScreenDeviceDao obtainIOperationScreenDeviceDao() throws Exception {
		return (IOperationScreenDeviceDao) cApplicationContext.getBean("operationScreenDeviceDao");
	}
    /**
     * 
     * <p>Title: structureSreenRoomContrast</p>   
     * <p>Description: 在IP对应的SreenDevice上添加(Vector push)ROOMCODE对应的ConsultingRoom映射关系</p>   
     * @param roomCode
     * @param internetProtocol
     * @throws Exception   
     * @see com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService#structureSreenRoomContrast(java.lang.String, java.lang.String)
     */
	@Override
	public void structureSreenRoomContrast(String roomCode, String internetProtocol) throws Exception {
		// TODO Auto-generated method stub
		if(!"".equals(FrameworkStringUtils.asString(roomCode))
				&&
				!"".equals(FrameworkStringUtils.asString(internetProtocol))){
			/**
			 * 添加诊室和屏设备的关系
			 */
			ScreenDeviceEntity screenDeviceEntity=obtainScreenDeviceByIPFromMap(internetProtocol);
			/**
			 * 在171.19.231.4对应的SreenDevice上添加(Vector push)ROOMCODE为304的ConsultingRoom映射关系
			 */
			if(screenDeviceEntity!=null){
				if(obtainIOperationConsultingRoomSerivce().existConsultingRoomEnityByRoomCode(roomCode)==true){
					screenDeviceEntity.pustRoomCodeToScreenDevice(roomCode);
					logger.info("在"+internetProtocol+"对应的SreenDevice上添加(Vector push)ROOMCODE为"+roomCode+"的ConsultingRoom映射关系");
				}else{
					
				}
			}else{
				
			}
			
		}
		
	}

	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce()throws Exception{
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}

	
	
}
