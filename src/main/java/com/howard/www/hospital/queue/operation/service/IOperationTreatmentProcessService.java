package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IOperationTreatmentProcessService {
	public JSONArray obtainDiagnosisAreaItemsInformation(IDataTransferObject queryParameters) throws Exception;
    
	public JSONObject obtainMedicalInformation(IDataTransferObject queryParameters)throws Exception;
}
