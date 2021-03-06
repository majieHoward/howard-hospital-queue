package com.howard.www.hospital.queue.operation.service;

import java.util.Vector;

import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.domain.ConsultingRoomEntity;

import net.sf.json.JSONArray;

public interface IOperationConsultingRoomSerivce extends IOperationBasicDataService {
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception;

	public ConsultingRoomEntity obtainConsultingRoomEnityByRoomCodeFromMap(String roomCode) throws Exception;

	public boolean existConsultingRoomEnityByRoomCode(String roomCode) throws Exception;

	public void structureSreenRoomContrast(String roomCode, String internetProtocol) throws Exception;

	public Vector<String> obtainCorrespondingScreenIpVectorInTheConsultingRoom(String roomCode) throws Exception;

	public Vector<String> obtainCorrespondingScreenIdentityVectorInTheConsultingRoom(String roomCode) throws Exception;
}
