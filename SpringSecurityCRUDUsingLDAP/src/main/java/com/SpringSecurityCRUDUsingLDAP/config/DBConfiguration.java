package com.SpringSecurityCRUDUsingLDAP.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DBConfiguration {
	@Autowired
    private Environment environment;
	
	
	  @Bean 
	  public DataSource dataSource() { 
		  DriverManagerDataSource ds = new DriverManagerDataSource();
	  ds.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
	  ds.setUrl(environment.getRequiredProperty("spring.datasource.url"));
	  ds.setUsername(environment.getRequiredProperty("spring.datasource.username"));
	  ds.setPassword(environment.getRequiredProperty("spring.datasource.password")); 
	  return ds; 
	  }
	 

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource());
		sf.setPackagesToScan(new String[] { "com.SpringSecurityCRUDUsingLDAP.model" });
		
		Properties prop = new Properties();
		prop.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		sf.setHibernateProperties(prop);

		return sf;

	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory().getObject());
		return htm;
	}

}
