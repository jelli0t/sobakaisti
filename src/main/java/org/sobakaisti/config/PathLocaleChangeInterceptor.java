/**
 * 
 */
package org.sobakaisti.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.util.StringUtil;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author jelli0t
 *
 */
public class PathLocaleChangeInterceptor extends HandlerInterceptorAdapter {	
	private static final Logger logger = LoggerFactory.getLogger(PathLocaleChangeInterceptor.class);	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			Object param = request.getAttribute(StringUtil.LANG_ATTRIBUTE_NAME);
			String langCode = param != null ? (String) param : null;
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			if(StringUtil.notEmpty(langCode)) {				
		        localeResolver.setLocale(request, response, new Locale(langCode));
		        logger.info("Postavljen locale na: "+langCode);
			} else {
				localeResolver.setLocale(request, response, new Locale(StringUtil.DEFAULT_LANG_CODE));
				logger.info("Nisam pronasao parametar lang. Postavljam locale na: "+StringUtil.DEFAULT_LANG_CODE);
			}			
		} catch (Exception e) {
			logger.warn("Greska prilikom postavljanja locale iz request parametra. Uzrok: "+e.getMessage());
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Locale locale = RequestContextUtils.getLocale(request);
		if(locale != null && !locale.getLanguage().equals(StringUtil.DEFAULT_LANG_CODE)) {
			logger.info("postavlam parametar 'lang' na: "+locale.getLanguage());
			modelAndView.addObject(StringUtil.LANG_ATTRIBUTE_NAME, locale.getLanguage() + StringUtil.SLASH_CHAR);
		}
	}
}
