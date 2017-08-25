package com.howard.www.hospital.queue.operation.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IOperationDoctorAttributeDao {
	public JSONArray obtainDoctorItemsAttribute(IDataTransferObject queryParameters) throws Exception;
}
