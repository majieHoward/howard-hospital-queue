package com.howard.www.hospital.queue.operation.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONObject;

public interface IExternalProvidedHisDao {
	public JSONObject callAPatientToSeeADoctor(IDataTransferObject paramDto) throws Exception;
}
