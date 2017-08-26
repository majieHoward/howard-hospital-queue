package com.howard.www.hospital.queue.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.howard.www.core.base.dao.impl.BaseDaoImpl;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.dao.IOperationScreenDeviceDao;

import net.sf.json.JSONArray;
@Repository("operationScreenDeviceDao")
public class OperationScreenDeviceDaoImpl extends BaseDaoImpl implements IOperationScreenDeviceDao {

	@Override
	public JSONArray obtainScreenDeviceInfo(IDataTransferObject queryParameters) throws Exception {
		// TODO Auto-generated method stub
		return obtainQuery()
				.evaluetePrimitiveSqlResource(
						"SELECT "
						+ "device.internet_protocol AS IP,"
						+ "device.screen_device_identity AS SDI,"
						+ "device.serial_number AS SN,"
						+ "device.available AS DA,"
						+ "device.screen_device_specification AS SDS,"
						+ "device.android_id AS AI,"
						+ "specification.screen_serial_encoding AS SSE,"
						+ "specification.page_identity AS PI,"
						+ "specification.screen_serial_describe AS SSD,"
						+ "specification.available AS SA,"
						+ "page.page_identity AS PI,"
						+ "page.page_name AS PN,"
						+ "page.page_describe AS PS,"
						+ "page.page_url_address AS PUA,"
						+ "page.available AS PA "
						+ "FROM "
						+ "hhq_screen_device AS device "
						+ "INNER JOIN "
						+ "hhq_screen_specification AS specification "
						+ "ON "
						+ "specification.screen_serial_encoding = device.screen_device_specification "
						+ "INNER JOIN "
						+ "hhq_page_display AS page "
						+ "ON "
						+ "specification.page_identity = page.page_identity "
						+ "WHERE "
						+ "device.available = '10A' "
						+ "AND "
						+ "specification.available = '10A' "
						+ "AND "
						+ "page.available = '10A'")
				.evaluateJsonNamedParameterJdbcTemplate("systemJdbcTemplate")
				.evaluateIDataTransferObject(queryParameters).forJsonArray();
	}

}
