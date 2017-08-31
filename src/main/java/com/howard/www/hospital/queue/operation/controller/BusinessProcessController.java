package com.howard.www.hospital.queue.operation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.service.IOperationBusinessProcessService;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;

@RestController
public class BusinessProcessController {
	protected final Logger log = LoggerFactory.getLogger(ExternalProvidedHisController.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	/**
	 * 
	 * @Title: obtainMedicalInformation @Description: TODO @param: @param
	 * requiredParameter @param: @return @param: @throws Exception @return:
	 * String @throws
	 */
	public String obtainMedicalInformation(IDataTransferObject requiredParameter) throws Exception {
		return null;
	}

	/**
	 * 
	 * @Title: obtainDiagnosisAreaItemsInformation @Description: TODO
	 * 获取诊区的所有诊室的信息(包括诊室和所安排的医生) @param: @param
	 * requiredParameter @param: @return @param: @throws Exception @return:
	 * String @throws
	 */
	@RequestMapping("/business/hospital/queue/diagnosis.area.items.information.obtain")
	public String obtainDiagnosisAreaItemsInformation(IDataTransferObject requiredParameter) throws Exception {
		return FrameworkStringUtils.asString(
				obtainIOperationBusinessProcessService().obtainDiagnosisAreaItemsInformation(requiredParameter));
	}

	@RequestMapping("/business/hospital/queue/at.certain.times.doctor.obtain")
	public String obtainAtCertainTimesDoctor(IDataTransferObject requiredParameter) throws Exception {
		return FrameworkStringUtils
				.asString(obtainIOperationDoctorSchedulingService().obtainAtCertainTimesDoctor(requiredParameter));
	}

	private IOperationDoctorSchedulingService obtainIOperationDoctorSchedulingService() throws Exception {
		return (IOperationDoctorSchedulingService) cApplicationContext.getBean("operationDoctorSchedulingService");
	}

	private IOperationBusinessProcessService obtainIOperationBusinessProcessService() throws Exception {
		return (IOperationBusinessProcessService) cApplicationContext.getBean("operationBusinessProcessService");
	}
}
