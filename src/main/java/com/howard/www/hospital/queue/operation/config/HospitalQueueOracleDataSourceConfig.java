package com.howard.www.hospital.queue.operation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

//@Configuration
//@EnableTransactionManagement
public class HospitalQueueOracleDataSourceConfig {
	@Bean(name = "hospitalQueueOracleDataSource")
	public DruidDataSource initHospitalQueueOracleDruidDataSource() throws Exception {
		DruidDataSource hospitalQueueOracleDataSource = new DruidDataSource();
		hospitalQueueOracleDataSource.setUrl(HospitalQueueDataSourceToConfigure.businessOracleDataSourceUrl);
		hospitalQueueOracleDataSource.setUsername(HospitalQueueDataSourceToConfigure.businessOracleDataSourceUserName);
		hospitalQueueOracleDataSource.setPassword(HospitalQueueDataSourceToConfigure.businessOracleDataSourcePassword);
		/* <property name="filters" value="stat" /> */
		hospitalQueueOracleDataSource.setFilters("stat");
		/* <property name="maxActive" value="20" /> */
		hospitalQueueOracleDataSource.setMaxActive(20);
		/* <property name="initialSize" value="1" /> */
		hospitalQueueOracleDataSource.setInitialSize(1);
		/* <property name="maxWait" value="60000" /> */
		hospitalQueueOracleDataSource.setMaxWait(60000);
		/* <property name="minIdle" value="1" /> */
		hospitalQueueOracleDataSource.setMinIdle(1);
		/* <property name="timeBetweenEvictionRunsMillis" value="3600000" /> */
		hospitalQueueOracleDataSource.setTimeBetweenEvictionRunsMillis(3600000);
		/* <property name="minEvictableIdleTimeMillis" value="3600000" /> */
		hospitalQueueOracleDataSource.setMinEvictableIdleTimeMillis(3600000);
		/* <property name="validationQuery" value="SELECT 'x'" /> */
		hospitalQueueOracleDataSource.setValidationQuery(HospitalQueueDataSourceToConfigure.businessOracleValidationQuery);
		/* <property name="testWhileIdle" value="true" /> */
		hospitalQueueOracleDataSource.setTestWhileIdle(true);
		/* <property name="testOnBorrow" value="false" /> */
		hospitalQueueOracleDataSource.setTestOnBorrow(false);
		/* <property name="testOnReturn" value="false" /> */
		hospitalQueueOracleDataSource.setTestOnReturn(false);
		/* <property name="poolPreparedStatements" value="true" /> */
		hospitalQueueOracleDataSource.setPoolPreparedStatements(true);
		/*
		 * <property name="maxPoolPreparedStatementPerConnectionSize" value="50"
		 * />
		 */
		hospitalQueueOracleDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
		return hospitalQueueOracleDataSource;
	}

	@Bean(name = "HospitalQueueOracleJdbcTemplate")
	public NamedParameterJdbcTemplate initHospitalQueueOracleDruidJdbcTemplate(
			@Qualifier("hospitalQueueOracleDataSource") DruidDataSource hospitalQueueOracleDataSource) throws Exception {
		return new NamedParameterJdbcTemplate(hospitalQueueOracleDataSource);
	}

}
