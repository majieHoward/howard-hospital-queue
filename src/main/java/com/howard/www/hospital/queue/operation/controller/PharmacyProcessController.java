package com.howard.www.hospital.queue.operation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

@RestController
public class PharmacyProcessController {
	protected final Logger log = LoggerFactory.getLogger(PharmacyProcessController.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	/**
	 * 
	 * @Title: outpatientPharmacyStation   
	 * @Description: TODO 门诊中药房叫号
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/pharmacy/hospital/queue/outpatient.tcm.pharmacy.station.process")
	public String outpatientTcmPharmacyStation(IDataTransferObject requiredParameter) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @Title: outpatientDispensaryStation   
	 * @Description: TODO门诊西药房叫号  
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/pharmacy/hospital/queue/outpatient.dispensary.station.process")
	public String outpatientDispensaryStation(IDataTransferObject requiredParameter) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @Title: outpatientPharmacyFreeFriedStation   
	 * @Description: TODO 门诊免煎药房叫号
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/pharmacy/hospital/queue/outpatient.pharmacy.free.fried.station.process")
	public String outpatientPharmacyFreeFriedStation(IDataTransferObject requiredParameter) throws Exception {
		return null;
	}
}
