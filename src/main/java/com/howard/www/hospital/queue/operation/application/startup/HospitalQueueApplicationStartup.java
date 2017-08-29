package com.howard.www.hospital.queue.operation.application.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenConsultingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;


public class HospitalQueueApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueApplicationStartup.class);
	private ApplicationContext cApplicationContext;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		log.info("Interface to be implemented by application event listeners. Based on the standard java.util.EventListener interface for the Observer design pattern.");
		cApplicationContext = event.getApplicationContext();
		try {
			//初始化诊室列表
			//obtainIOperationConsultingRoomSerivce().initializingServiceBaseData(new DataTransferObject());
			//初始化医生列表(包括医生简介)
			//obtainIOperationDoctorAttributeService().initializingServiceBaseData(new DataTransferObject());
			//初始化设备列表(包含设备类型对应的页面关系)
			//obtainIOperationScreenDeviceService().initializingServiceBaseData(new DataTransferObject());
			//构造诊室和设备的对照关系
			//obtainIOperationScreenConsultingService().initializingServiceBaseData(new DataTransferObject());
			//其他的关系可以暂时不初始化
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce()throws Exception{
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}
	
	private IOperationDoctorAttributeService obtainIOperationDoctorAttributeService()throws Exception{
		return (IOperationDoctorAttributeService) cApplicationContext.getBean("operationDoctorAttributeService");
	}
	
	private IOperationScreenDeviceService obtainIOperationScreenDeviceService()throws Exception{
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}
	
	private IOperationScreenConsultingService obtainIOperationScreenConsultingService()throws Exception{
		return (IOperationScreenConsultingService) cApplicationContext.getBean("operationScreenConsultingService");
	}
}
