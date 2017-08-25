package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;
import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationDoctorAttributeDao;
import net.sf.json.JSONArray;
/**
 * 
 * @ClassName:  OperationDoctorAttributeDaoImpl   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年8月25日 下午2:54:40   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("operationDoctorAttributeDao")
public class OperationDoctorAttributeDaoImpl extends BaseDaoImpl implements IOperationDoctorAttributeDao {

	@Override
	public JSONArray obtainDoctorItemsAttribute(IDataTransferObject queryParameters) throws Exception {
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"SELECT "
						+ "doctor.ID AS I,"
						+ "doctor.NAME AS N,"
						+ "doctor.GROUP_NAME AS GN,"
						+ "doctor.TITLE AS T,"
						+ "doctor.DEPT_CODE AS DC "
						+ "FROM "
						+ "v_outp_doctor doctor")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
