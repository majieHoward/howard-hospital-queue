package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IOperationScreenConsultingService extends IOperationBasicDataService{
	public JSONArray obtainScreenConsultingInfo(IDataTransferObject queryParameters) throws Exception;
}
