package com.howard.www.hospital.queue.operation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.howard.www.core.hbatis.datasource.jdbctemplate.FrameworkResetDataSourceOfJdbcTemplate;

@Configuration
@EnableTransactionManagement
public class HospitalQueueSystemDataSourceConfig {
	
	@Bean(name = "systemDataSource")
	@Primary
	public DruidDataSource initDruidDataSource() throws Exception {
		DruidDataSource systemDataSource = new DruidDataSource();
		systemDataSource.setUrl(HospitalQueueDataSourceToConfigure.businessMysqlDataSourceUrl);
		systemDataSource.setUsername(HospitalQueueDataSourceToConfigure.businessMysqlDataSourceUserName);
		systemDataSource.setPassword(HospitalQueueDataSourceToConfigure.businessMysqlDataSourcePassword);
		/* <property name="filters" value="stat" /> */
		systemDataSource.setFilters("stat");
		/* <property name="maxActive" value="20" /> */
		systemDataSource.setMaxActive(20);
		/* <property name="initialSize" value="1" /> */
		systemDataSource.setInitialSize(1);
		/* <property name="maxWait" value="60000" /> */
		systemDataSource.setMaxWait(60000);
		/* <property name="minIdle" value="1" /> */
		systemDataSource.setMinIdle(1);
		/* <property name="timeBetweenEvictionRunsMillis" value="3600000" /> */
		systemDataSource.setTimeBetweenEvictionRunsMillis(3600000);
		/* <property name="minEvictableIdleTimeMillis" value="3600000" /> */
		systemDataSource.setMinEvictableIdleTimeMillis(3600000);
		/* <property name="validationQuery" value="SELECT 'x'" /> */
		systemDataSource.setValidationQuery(HospitalQueueDataSourceToConfigure.businessMysqlValidationQuery);
		/* <property name="testWhileIdle" value="true" /> */
		systemDataSource.setTestWhileIdle(true);
		/* <property name="testOnBorrow" value="false" /> */
		systemDataSource.setTestOnBorrow(false);
		/* <property name="testOnReturn" value="false" /> */
		systemDataSource.setTestOnReturn(false);
		/* <property name="poolPreparedStatements" value="true" /> */
		systemDataSource.setPoolPreparedStatements(true);
		/*
		 * <property name="maxPoolPreparedStatementPerConnectionSize" value="50"
		 * />
		 */
		systemDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
		return systemDataSource;
	}
	
	@Bean(name = "systemJdbcTemplate")
	public NamedParameterJdbcTemplate initDruidJdbcTemplate(@Qualifier("systemDataSource") DruidDataSource druidDataSource) throws Exception {
		return  new NamedParameterJdbcTemplate(druidDataSource);
	}

	@Bean(name = "systemResetDataSource")
	public FrameworkResetDataSourceOfJdbcTemplate initFrameworkResetDataSourceOfJdbcTemplate() throws Exception {
		return new FrameworkResetDataSourceOfJdbcTemplate();
	}
	
}
