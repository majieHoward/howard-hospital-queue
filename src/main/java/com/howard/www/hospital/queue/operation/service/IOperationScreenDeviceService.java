package com.howard.www.hospital.queue.operation.service;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.ScreenDeviceEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IOperationScreenDeviceService extends IOperationBasicDataService{
	
	public JSONArray obtainScreenDeviceInfo(IDataTransferObject queryParameters) throws Exception;

    public ScreenDeviceEntity obtainScreenDeviceByIPFromMap(String internetProtocol)throws Exception;
    
    public ScreenDeviceEntity obtainScreenDeviceByIPFromMap(IDataTransferObject queryParameters)throws Exception;
    
    public JSONObject obtainHomeScreenDisplayPage(IDataTransferObject queryParameters)throws Exception;
    
    public boolean existScreenDeviceByIP(String internetProtocol)throws Exception;

    public ScreenDeviceEntity obtainScreenDeviceByIdentityFromMap(String screenDeviceIdentity)throws Exception;

    public void structureSreenRoomContrast(String roomCode,String internetProtocol)throws Exception;
    
    public String obtainHomeScreenDisplayPageURI(IDataTransferObject queryParameters)throws Exception;
    
    public String obtainHomeScreenDisplayPageURIByIP(String internetProtocol)throws Exception;
    
    public boolean internetProtocolCheck(String internetProtocol) throws Exception;
}
