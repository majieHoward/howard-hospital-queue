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
import com.howard.www.hospital.queue.operation.service.IOperationBusinessProcessService;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("operationBusinessProcessService")
public class OperationBusinessProcessServiceImpl implements IOperationBusinessProcessService {
	private static final Logger logger = LoggerFactory.getLogger(OperationBusinessProcessServiceImpl.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	@Override
	public JSONArray obtainDiagnosisAreaItemsInformation(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		String internetProtocol = FrameworkStringUtils
				.asString(queryParameters.obtainMapOfRequiredParameter().get("internetProtocol"));
		String time = FrameworkStringUtils.asString(queryParameters.obtainMapOfRequiredParameter().get("time"));
		if ("".equals(internetProtocol)) {
			throw new RuntimeException("");
		}
		ScreenDeviceEntity screenDeviceEntity = obtainIOperationScreenDeviceService()
				.obtainScreenDeviceByIPFromMap(internetProtocol);
		if (screenDeviceEntity != null) {
			Vector<String> roomCodeItems = screenDeviceEntity.getConsultingRoomCodeItems();
			if (roomCodeItems != null && roomCodeItems.size() > 0) {
				Vector<Object> diagnosisAreaItems=new Vector<Object>();
				ConsultingRoomEntity consultingRoomEntity;
				for (String roomCode : roomCodeItems) {
					consultingRoomEntity=obtainIOperationConsultingRoomSerivce().obtainConsultingRoomEnityByRoomCodeFromMap(roomCode);
					if(consultingRoomEntity!=null){
						consultingRoomEntity.setScheduling(obtainIOperationDoctorSchedulingService().obtainAtCertainTimesDoctor(roomCode,
								time));
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
