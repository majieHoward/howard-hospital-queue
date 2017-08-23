package com.howard.www.hospital.queue.operation.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.impl.OperationConsultingRoomSerivceImpl;

@Configuration
public class HospitalConsultingRoomAttributeInitialization {
	protected final Logger log = LoggerFactory.getLogger(HospitalConsultingRoomAttributeInitialization.class);
	@Bean(name = "operationConsultingRoomSerivce")
	public IOperationConsultingRoomSerivce constructionConsultingRoomSerivce() throws Exception {
		IOperationConsultingRoomSerivce consultingRoomSerivce = new OperationConsultingRoomSerivceImpl();
		log.info("initialize consulting room information");
		return consultingRoomSerivce;
	}
}
