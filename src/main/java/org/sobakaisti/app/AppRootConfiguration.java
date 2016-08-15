/**
 * 
 */
package org.sobakaisti.app;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.sobakaisti.mvt.dao.impl.AccountManagerDaotImpl;
import org.sobakaisti.mvt.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jelles
 *
 */
@Configuration
@ComponentScan({"org.sobakaisti.app"})
@EnableTransactionManagement
@Import(AppSecurityConfiguration.class)
public class AppRootConfiguration {
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sobakaisti_database");
		dataSource.setUsername("root");
		dataSource.setPassword("");	//root
		return dataSource;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
		sessionFactory.addAnnotatedClass(Account.class);
		sessionFactory.addProperties(getHibernateProperties());
		return sessionFactory.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="transactionManager")
	HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
		return txManager;
	}
	@Autowired
	@Bean(name="accountManagerDao")
	public AccountManagerDao getAccuntManagerDao(SessionFactory sessionFactory){
		AccountManagerDao accountManagment = new AccountManagerDaotImpl(sessionFactory);
		return accountManagment;
	}
	
	private Properties getHibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return hibernateProperties;	
	}	
}
