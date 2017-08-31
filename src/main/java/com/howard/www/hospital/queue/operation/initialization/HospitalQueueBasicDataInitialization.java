package com.howard.www.hospital.queue.operation.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenConsultingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;
import com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService;
import com.howard.www.hospital.queue.operation.service.impl.OperationConsultingRoomSerivceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationDoctorAttributeServiceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationDoctorSchedulingServiceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationScreenConsultingServiceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationScreenDeviceServiceImpl;
import com.howard.www.hospital.queue.operation.service.impl.OperationSubscribeMessageServiceImpl;

@Configuration
public class HospitalQueueBasicDataInitialization {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueBasicDataInitialization.class);
	
	@Bean(name = "operationConsultingRoomSerivce")
	public IOperationConsultingRoomSerivce constructionConsultingRoomSerivce() throws Exception {
		log.info("initialize bean name is operationConsultingRoomSerivce");
		return new OperationConsultingRoomSerivceImpl();
	}
	
	@Bean(name = "operationDoctorAttributeService")
	public IOperationDoctorAttributeService constructionDoctorAttributeService()throws Exception{
		log.info("initialize bean name is operationDoctorAttributeService");
		return new OperationDoctorAttributeServiceImpl();
	}
	
	@Bean(name = "operationScreenDeviceService")
	public IOperationScreenDeviceService constructionScreenDeviceService()throws Exception{
		log.info("initialize bean name is operationScreenDeviceService");
		return new OperationScreenDeviceServiceImpl();
	}
	
	@Bean(name = "operationScreenConsultingService")
	public IOperationScreenConsultingService constructionScreenConsultingService()throws Exception{
		log.info("initialize bean name is operationScreenConsultingService");
		return new OperationScreenConsultingServiceImpl();
	}
	
	@Bean(name = "operationSubscribeMessageService")
	public IOperationSubscribeMessageService constructionSubscribeMessageService()throws Exception{
		log.info("initialize bean name is operationScreenConsultingService");
		return new OperationSubscribeMessageServiceImpl();
	} 
	
	@Bean(name = "operationDoctorSchedulingService")
	public IOperationDoctorSchedulingService constructionDoctorSchedulingService()throws Exception{
		log.info("initialize bean name is operationDoctorSchedulingService");
		return new OperationDoctorSchedulingServiceImpl();
	} 
}
