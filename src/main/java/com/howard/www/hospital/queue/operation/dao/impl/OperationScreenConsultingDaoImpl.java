package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationScreenConsultingDao;

import net.sf.json.JSONArray;
@Repository("operationScreenConsultingDao")
public class OperationScreenConsultingDaoImpl extends BaseDaoImpl implements IOperationScreenConsultingDao {
    /**
     * 
     * <p>Title: obtainScreenConsultingInfo</p>   
     * <p>Description: 获取屏幕设备与诊室直接的关系</p>   
     * @param queryParameters
     * @return
     * @throws Exception   
     * @see com.howard.www.hospital.queue.operation.dao.IOperationScreenConsultingDao#obtainScreenConsultingInfo(com.howard.www.core.data.transfer.dto.IDataTransferObject)
     */
	@Override
	public JSONArray obtainScreenConsultingInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"SELECT "
						+ "sreen.internet_protocol AS IP,"
						+ "sreen.screen_device_identity AS SDI,"
						+ "sreen.serial_number AS SN,"
						+ "room.ROOM_CODE AS RC,"
						+ "room.ROOM_NAME AS RN,"
						+ "sreen.available AS SA,"
						+ "relation.available AS RA,"
						+ "sreen.screen_device_specification AS SDS,"
						+ "sreen.android_id AS AI "
						+ "FROM "
						+ "hhq_screen_device AS sreen "
						+ "INNER JOIN "
						+ "hhq_screen_consulting AS relation "
						+ "ON "
						+ "sreen.screen_device_identity = relation.screen_device_identity "
						+ "INNER JOIN "
						+ "room_dict AS room "
						+ "ON room.ROOM_CODE = relation.room_code "
						+ "where "
						+ "relation.available='10A' "
						+ "AND "
						+ "sreen.available='10A'")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
