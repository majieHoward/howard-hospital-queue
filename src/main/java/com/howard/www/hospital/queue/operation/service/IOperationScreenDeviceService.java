package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.ScreenDeviceEntity;

import net.sf.json.JSONArray;

public interface IOperationScreenDeviceService extends IOperationBasicDataService{
	public JSONArray obtainScreenDeviceInfo(IDataTransferObject queryParameters) throws Exception;

    public ScreenDeviceEntity obtainScreenDeviceByIPFromMap(String internetProtocol)throws Exception;
    
    public boolean existScreenDeviceByIP(String internetProtocol)throws Exception;

    public ScreenDeviceEntity obtainScreenDeviceByIdentityFromMap(String screenDeviceIdentity)throws Exception;

    public void structureSreenRoomContrast(String roomCode,String internetProtocol)throws Exception;
}
