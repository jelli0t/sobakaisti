package org.sobakaisti.app;

import javax.servlet.Filter;

import org.sobakaisti.mvt.MvtWebMvcConfiguration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {
				AppSecurityConfiguration.class,
				AppRootConfiguration.class,
				org.sobakaisti.app.MailConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvtWebMvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		 return new Filter[] {
				 new CharacterEncodingFilter("UTF-8", true)
		 };
	}
}
