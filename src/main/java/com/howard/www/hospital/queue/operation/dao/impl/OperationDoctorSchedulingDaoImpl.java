package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationDoctorSchedulingDao;

import net.sf.json.JSONArray;

@Repository("operationDoctorSchedulingDao")
public class OperationDoctorSchedulingDaoImpl extends BaseDaoImpl implements IOperationDoctorSchedulingDao {

	@Override
	public JSONArray obtainCurrentDoctorSchedulingInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"SELECT "
						+ "t.doctor AS D,"
						+ "t.room_code AS RC,"
						+ "t.visit_time_desc AS VTD,"
						+ "t.visit_date AS VD "
						+ "FROM "
						+ "v_jiaohao t "
						+ "GROUP BY "
						+ "t.doctor,"
						+ "t.room_code,"
						+ "t.visit_time_desc,"
						+ "t.visit_date "
						+ "ORDER BY "
						+ "t.room_code")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
