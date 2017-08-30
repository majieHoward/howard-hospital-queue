package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.DoctorAttributeEntity;

import net.sf.json.JSONArray;

public interface IOperationDoctorAttributeService extends IOperationBasicDataService{
	
	public JSONArray obtainDoctorAttributeInfo(IDataTransferObject queryParameters) throws Exception;

	public boolean existDoctorAttributeEntityByDoctorJobNumber(String doctorJobNumber) throws Exception;

	public DoctorAttributeEntity obtainDoctorAttributeEntityByDoctorJobNumberFromMap(String doctorJobNumber) throws Exception; 
}
