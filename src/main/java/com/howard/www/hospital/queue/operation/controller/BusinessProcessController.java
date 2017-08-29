package com.howard.www.hospital.queue.operation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

@RestController
public class BusinessProcessController {
	protected final Logger log = LoggerFactory.getLogger(ExternalProvidedHisController.class);
	@Autowired
	private ApplicationContext cApplicationContext;
	
	public String obtainMedicalInformation(IDataTransferObject requiredParameter)throws Exception{
		return null;
	}
	
}
