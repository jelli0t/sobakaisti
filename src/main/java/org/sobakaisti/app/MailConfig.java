package org.sobakaisti.app;

import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * @author jelles
 *
 */
@Configuration
@PropertySource("classpath:mail/mail.properties")
@ComponentScan({ "org.sobakaisti.mail" })
public class MailConfig implements ApplicationContextAware {

	private static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
	
	@Autowired
	private Environment env;
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}

	@Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/messages");
        return messageSource;
    }
	
	@Bean(name="mailSender")
	public JavaMailSender configureMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		/* SMTP props */
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));		
//		properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
//		properties.setProperty("mail.debug", env.getProperty("mail.debug"));
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
	
	@Bean(name="mailTemplateEngine")
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		// Resolver for TEXT emails
		templateEngine.addTemplateResolver(textTemplateResolver());
		// Resolver for HTML emails (except the editable one)
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		// Resolver for HTML editable emails (which will be treated as a String)
//		templateEngine.addTemplateResolver(stringTemplateResolver());
		// Message source, internationalization specific to emails
		templateEngine.setTemplateEngineMessageSource(emailMessageSource());
		return templateEngine;
	}

	private ITemplateResolver textTemplateResolver() {
//		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setOrder(Integer.valueOf(1));
//		templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
		templateResolver.setPrefix("/WEB-INF/templates/mail/");
		templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setOrder(Integer.valueOf(2));
//		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("/WEB-INF/templates/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	private ITemplateResolver stringTemplateResolver() {
		final StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setOrder(Integer.valueOf(3));
		// No resolvable pattern, will simply process as a String template
		// everything not previously matched
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	
}
