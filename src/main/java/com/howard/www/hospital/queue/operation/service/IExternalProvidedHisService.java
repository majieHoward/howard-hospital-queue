package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

public interface IExternalProvidedHisService {
	public JSONObject callAPatientToSeeADoctor(IDataTransferObject paramDto) throws Exception;
}
