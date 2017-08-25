package com.howard.www.hospital.queue.operation.application.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;


public class HospitalQueueApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueApplicationStartup.class);
	private ApplicationContext cApplicationContext;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		log.info("Interface to be implemented by application event listeners. Based on the standard java.util.EventListener interface for the Observer design pattern.");
		cApplicationContext = event.getApplicationContext();
		try {
			obtainIOperationConsultingRoomSerivce().obtainConsultingRoomInfo(new DataTransferObject());
			obtainIOperationDoctorAttributeService().obtainDoctorAttributeInfo(new DataTransferObject());
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
}
