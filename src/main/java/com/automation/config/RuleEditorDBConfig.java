/**
 * 
 */
package com.automation.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author AvinashVaidya
 *
 */
@Configuration
@EnableTransactionManagement
@ImportResource("classpath:Spring-RULEDB-Database.xml")
public class RuleEditorDBConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(){
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	
		transactionManager.setSessionFactory(entityManagerFactoryBean()
				.getObject());
		return transactionManager;
	}
	 
	
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean entityManagerFactoryBean() {
		

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		factoryBean.setDataSource(dataSource);

		factoryBean.setPackagesToScan(new String[] { "com.automation.dto" });

		// hibernate properties
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", "500");
		hibernateProperties.setProperty("order_inserts", "true");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("minPoolSize", "5");
		hibernateProperties.setProperty("acquireIncrement", "5");
		hibernateProperties.setProperty("maxPoolSize", "125");
		hibernateProperties.setProperty("maxStatements", "20");
		hibernateProperties.setProperty("maxIdleTime", "180");
		hibernateProperties.setProperty("maxIdleTimeExcessConnections", "30");
		hibernateProperties.setProperty("checkoutTimeout", "1000000");
		hibernateProperties.setProperty("preferredTestQuery", "SELECT 1");

		factoryBean.setHibernateProperties(hibernateProperties);
		return factoryBean;
	}

	
	
}
