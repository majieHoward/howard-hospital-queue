package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationConsultingRoomDao;

import net.sf.json.JSONArray;
@Repository("operationConsultingRoomDao")
public class OperationConsultingRoomDaoImpl extends BaseDaoImpl implements IOperationConsultingRoomDao {

	@Override
	public JSONArray obtainConsultingRoomInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"select "
						+ "substr(t.room_name,0,INSTR(t.room_name,'（')-1) zsm,"
						+ "substr(t.room_name,INSTR(t.room_name,'（'),INSTR(t.room_name,'）')-INSTR(t.room_name,'（')+1) lc,"
						+ "t.* "
						+ "from room_dict t "
						+ "order by lc,zsm")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
