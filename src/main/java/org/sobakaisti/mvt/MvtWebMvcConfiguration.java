package org.sobakaisti.mvt;

import java.util.Locale;

import org.sobakaisti.mvt.service.MediaService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @author jelles
 * MVC Configuration for Movement part of Application
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:variables.properties")
@ComponentScan(basePackages="org.sobakaisti.mvt")
public class MvtWebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	@Value( "${media.uploads.path.file}" ) private String imgUploadsFilePath;	
	private ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("File: "+imgUploadsFilePath);
		registry.addResourceHandler("/resources/**", "/uploads/images/**")
				.addResourceLocations("/resources/", imgUploadsFilePath);
	}

	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResover = new SessionLocaleResolver();
		localeResover.setDefaultLocale(new Locale("rs"));
		return localeResover;
	}
	@Bean
	  public ViewResolver viewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	  }

	  @Bean
	  public TemplateEngine templateEngine() {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }
	  
	  private ITemplateResolver templateResolver() {
	    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	    resolver.setApplicationContext(applicationContext);
	    resolver.setPrefix("/WEB-INF/templates/");
	    resolver.setSuffix(".html");
	    resolver.setTemplateMode(TemplateMode.HTML);
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	  }
	  
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	@Bean
	 public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	 }
}
