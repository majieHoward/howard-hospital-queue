package com.howard.www.hospital.queue.operation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
public class HospitalQueueOracleDataSourceConfig {
	@Bean(name = "hospitalQueueOracleDataSouce")
	public DruidDataSource initHospitalQueueOracleDruidDataSource() throws Exception {
		DruidDataSource hospitalQueueOracleDataSouce = new DruidDataSource();
		hospitalQueueOracleDataSouce.setUrl("jdbc:oracle:thin:@133.37.117.61:1521:comm");
		hospitalQueueOracleDataSouce.setUsername("comm");
		hospitalQueueOracleDataSouce.setPassword("123qaz");
		/* <property name="filters" value="stat" /> */
		hospitalQueueOracleDataSouce.setFilters("stat");
		/* <property name="maxActive" value="20" /> */
		hospitalQueueOracleDataSouce.setMaxActive(20);
		/* <property name="initialSize" value="1" /> */
		hospitalQueueOracleDataSouce.setInitialSize(1);
		/* <property name="maxWait" value="60000" /> */
		hospitalQueueOracleDataSouce.setMaxWait(60000);
		/* <property name="minIdle" value="1" /> */
		hospitalQueueOracleDataSouce.setMinIdle(1);
		/* <property name="timeBetweenEvictionRunsMillis" value="3600000" /> */
		hospitalQueueOracleDataSouce.setTimeBetweenEvictionRunsMillis(3600000);
		/* <property name="minEvictableIdleTimeMillis" value="3600000" /> */
		hospitalQueueOracleDataSouce.setMinEvictableIdleTimeMillis(3600000);
		/* <property name="validationQuery" value="SELECT 'x'" /> */
		hospitalQueueOracleDataSouce.setValidationQuery("SELECT 1 FROM DUAL");
		/* <property name="testWhileIdle" value="true" /> */
		hospitalQueueOracleDataSouce.setTestWhileIdle(true);
		/* <property name="testOnBorrow" value="false" /> */
		hospitalQueueOracleDataSouce.setTestOnBorrow(false);
		/* <property name="testOnReturn" value="false" /> */
		hospitalQueueOracleDataSouce.setTestOnReturn(false);
		/* <property name="poolPreparedStatements" value="true" /> */
		hospitalQueueOracleDataSouce.setPoolPreparedStatements(true);
		/*
		 * <property name="maxPoolPreparedStatementPerConnectionSize" value="50"
		 * />
		 */
		hospitalQueueOracleDataSouce.setMaxPoolPreparedStatementPerConnectionSize(50);
		return hospitalQueueOracleDataSouce;
	}

	@Bean(name = "HospitalQueueOracleJdbcTemplate")
	public NamedParameterJdbcTemplate initHospitalQueueOracleDruidJdbcTemplate(
			@Qualifier("hospitalQueueOracleDataSouce") DruidDataSource hospitalQueueOracleDataSouce) throws Exception {
		return new NamedParameterJdbcTemplate(hospitalQueueOracleDataSouce);
	}

}
