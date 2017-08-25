package com.howard.www.hospital.queue.operation.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.impl.OperationConsultingRoomSerivceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationDoctorAttributeServiceImpl;

@Configuration
public class HospitalQueueBasicDataInitialization {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueBasicDataInitialization.class);
	@Bean(name = "operationConsultingRoomSerivce")
	public IOperationConsultingRoomSerivce constructionConsultingRoomSerivce() throws Exception {
		IOperationConsultingRoomSerivce consultingRoomSerivce = new OperationConsultingRoomSerivceImpl();
		log.info("initialize bean name is operationConsultingRoomSerivce");
		return consultingRoomSerivce;
	}
	
	@Bean(name = "operationDoctorAttributeService")
	public IOperationDoctorAttributeService constructionDoctorAttributeService()throws Exception{
		IOperationDoctorAttributeService doctorAttributeService=new OperationDoctorAttributeServiceImpl();
		log.info("initialize bean name is operationDoctorAttributeService");
		return doctorAttributeService;
	}
}
