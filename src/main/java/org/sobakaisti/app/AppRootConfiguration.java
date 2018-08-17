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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jelles
 *
 */
@Configuration
@PropertySource("classpath:variables.properties")
@PropertySource(value = "${datasource.mysql.conf}", ignoreResourceNotFound = true)
@ComponentScan({"org.sobakaisti"})
@EnableTransactionManagement
public class AppRootConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.jdbc.driver"));
		dataSource.setUrl(env.getProperty("mysql.jdbc.url"));
		dataSource.setUsername(env.getProperty("mysql.jdbc.username"));
		dataSource.setPassword(env.getProperty("mysql.jdbc.password"));
		return dataSource;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
		sessionFactory.scanPackages("org.sobakaisti.mvt.models", "org.sobakaisti.mvt.i18n.model", "org.sobakaisti.security.model");
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
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
		
	private Properties getHibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
		hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
		hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
//		hibernateProperties.setProperty("hibernate.show_sql", "true");
//		hibernateProperties.setProperty("hibernate.format_sql", "true");
		return hibernateProperties;	
	}	
}
