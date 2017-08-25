package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IOperationDoctorAttributeService {
	public JSONArray obtainDoctorAttributeInfo(IDataTransferObject queryParameters) throws Exception;
}
