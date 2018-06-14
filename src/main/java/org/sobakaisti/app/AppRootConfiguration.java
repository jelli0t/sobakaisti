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
@PropertySources({
    @PropertySource("classpath:database.properties"),
    @PropertySource("classpath:mail.properties")
})
@ComponentScan({"org.sobakaisti"})
@EnableTransactionManagement
@Import(AppSecurityConfiguration.class)
public class AppRootConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sobakaisti?useUnicode=yes&characterEncoding=UTF-8");	// 3306
		dataSource.setUsername("root");
		dataSource.setPassword("");	//root 
		return dataSource;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
		sessionFactory.scanPackages("org.sobakaisti.mvt.models", "org.sobakaisti.mvt.i18n.model");
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
	
	@Bean(name="mailSender")
	public JavaMailSender configureMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		/* SMTP props */
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));		
		properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		properties.setProperty("mail.debug", env.getProperty("mail.debug"));
		properties.setProperty("mail.smtp.port", env.getProperty("mail.smtp.port"));
		properties.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));		
		properties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		
		mailSender.setHost(env.getProperty("mail.smtp.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mail.smtp.port")));
		mailSender.setProtocol(env.getProperty("mail.transport.protocol"));
		if(Boolean.parseBoolean(env.getProperty("mail.smtp.auth"))) {
			mailSender.setUsername(env.getProperty("mail.smtp.auth.username"));
			mailSender.setPassword(env.getProperty("mail.smtp.auth.password"));
		}
		mailSender.setJavaMailProperties(properties);
		return mailSender;
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
