package com.howard.www.hospital.queue.operation.service.impl;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.ConsultingRoomEntity;
import com.howard.www.hospital.queue.operation.domain.ScreenDeviceEntity;
import com.howard.www.hospital.queue.operation.service.IOperationTreatmentProcessService;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("operationTreatmentProcessService")
public class OperationTreatmentProcessServiceImpl implements IOperationTreatmentProcessService {
	private static final Logger logger = LoggerFactory.getLogger(OperationTreatmentProcessServiceImpl.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	/**
	 * 
	 * <p>Title: obtainDiagnosisAreaItemsInformation</p>   
	 * <p>Description: 获取诊区的所有诊室的信息(包括诊室和所安排的医生)</p>   
	 * @param queryParameters
	 * @return
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IOperationTreatmentProcessService#obtainDiagnosisAreaItemsInformation(com.howard.www.core.data.transfer.dto.IDataTransferObject)
	 */
	@Override
	public JSONArray obtainDiagnosisAreaItemsInformation(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		String internetProtocol = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("internetProtocol"));
		String time = FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("time"));
		if ("".equals(internetProtocol)) {
			throw new RuntimeException("");
		}
		/**
		 * 通过IP获取屏幕screenDeviceEntity详细信息
		 */
		ScreenDeviceEntity screenDeviceEntity = obtainIOperationScreenDeviceService()
				.obtainScreenDeviceByIPFromMap(internetProtocol);
		if (screenDeviceEntity != null) {
			/**
			 * 获取屏幕所包含的roomCodeItems诊室编号集
			 */
			Vector<String> roomCodeItems = screenDeviceEntity.getConsultingRoomCodeItems();
			if (roomCodeItems != null && roomCodeItems.size() > 0) {
				Vector<Object> diagnosisAreaItems=new Vector<Object>();
				ConsultingRoomEntity consultingRoomEntity;
				for (String roomCode : roomCodeItems) {
					/**
					 * 获取诊室编号为roomCode的诊室信息
					 */
					
					consultingRoomEntity=obtainIOperationConsultingRoomSerivce().obtainConsultingRoomEnityByRoomCodeFromMap(roomCode);
					if(consultingRoomEntity!=null){
						/**
						 * 获取诊室编号roomCode为当前坐诊的医生详细信息  
						 */
						consultingRoomEntity.setScheduling(obtainIOperationDoctorSchedulingService().obtainAtCertainTimesDoctor(roomCode,
								time));
						logger.info("获取ROOM_CODE为:"+roomCode+"的诊室信息和当前坐诊的医生详细信息");
						diagnosisAreaItems.add(consultingRoomEntity);
					}
					
				}
				return JSONArray.fromObject(diagnosisAreaItems);
			}
		}
		return new JSONArray();
	}

	@Override
	public JSONObject obtainMedicalInformation(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private IOperationScreenDeviceService obtainIOperationScreenDeviceService() throws Exception {
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}

	private IOperationDoctorSchedulingService obtainIOperationDoctorSchedulingService() throws Exception {
		return (IOperationDoctorSchedulingService) cApplicationContext.getBean("operationDoctorSchedulingService");
	}
	
	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce()throws Exception{
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}
}
