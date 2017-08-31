package com.howard.www.hospital.queue.operation.service;

import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.CurrentDoctorSchedulingEntity;
import com.howard.www.hospital.queue.operation.domain.DoctorAttributeEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IOperationDoctorSchedulingService extends IOperationBasicDataService{
	
	public JSONArray obtainCurrentDoctorSchedulingInfo(IDataTransferObject queryParameters)throws Exception;
	
	public ConcurrentHashMap<String,CurrentDoctorSchedulingEntity> obtainTimeIntervalSchedulingByRoomCode(String roomCode)throws Exception;

	public CurrentDoctorSchedulingEntity obtainAtCertainTimesSchedulingByRoomCodeAndTime(String roomCode,String time)throws Exception;

	public DoctorAttributeEntity obtainAtCertainTimesDoctorByRoomCodeAndTime(String roomCode,String time)throws Exception;

    public JSONObject obtainAtCertainTimesDoctor(IDataTransferObject queryParameters)throws Exception;
}
