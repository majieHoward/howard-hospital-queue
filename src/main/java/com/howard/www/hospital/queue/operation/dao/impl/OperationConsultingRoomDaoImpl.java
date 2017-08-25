package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationConsultingRoomDao;

import net.sf.json.JSONArray;
@Repository("operationConsultingRoomDao")
/**
 * 
 * @ClassName:  OperationConsultingRoomDaoImpl   
 * @Description:TODO   
 * @author: mayijie
 * @date:   2017年8月25日 下午2:54:52   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class OperationConsultingRoomDaoImpl extends BaseDaoImpl implements IOperationConsultingRoomDao {

	@Override
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select "
						+ "room.ROOM_CODE AS RC,"
						+ "room.ROOM_NAME AS RN,"
						+ "room.DEPT_CODE AS DC,"
						+ "room.ROOM_TYPE AS RT,"
						+ "room.SIMPLE_NAME AS SN,"
						+ "room.INPUT_CODE AS IC "
						+ "from "
						+ "room_dict room ")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
