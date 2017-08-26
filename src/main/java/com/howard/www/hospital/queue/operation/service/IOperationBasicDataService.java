package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;

public interface IOperationBasicDataService {
	public void initializingServiceBaseData(IDataTransferObject queryParameters)throws Exception;
}
