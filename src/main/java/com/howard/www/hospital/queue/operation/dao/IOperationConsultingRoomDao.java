package com.howard.www.hospital.queue.operation.dao;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

import net.sf.json.JSONArray;

public interface IOperationConsultingRoomDao {

	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception;
}
