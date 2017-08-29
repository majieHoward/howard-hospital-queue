package com.howard.www.hospital.queue.operation.config;

/**
 * 
 * @ClassName: HospitalQueueDataSourceToConfigure
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: mayijie
 * @date: 2017年8月29日 下午3:48:12
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public interface HospitalQueueDataSourceToConfigure {
	public String businessOracleDataSourceUrl = "jdbc:oracle:thin:@133.37.117.61:1521:comm";
	public String businessOracleDataSourceUserName = "comm";
	public String businessOracleDataSourcePassword = "123qaz";
	public String businessOracleValidationQuery = "SELECT 1 FROM DUAL";
	public String businessMysqlDataSourceUrl = "jdbc:mysql://127.0.0.1:3306/hospitalqueue?characterEncoding=UTF-8";
	public String businessMysqlDataSourceUserName = "root";
	public String businessMysqlDataSourcePassword = "majie";
	public String businessMysqlValidationQuery = "SELECT 'x'";
}
