/**
 * 
 */
package org.sobakaisti.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.util.StringUtil;
import org.springframework.web.filter.OncePerRequestFilter;

public class PathVariableLocaleFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(PathVariableLocaleFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String uri = request.getRequestURI().substring(request.getContextPath().length());
		String[] pathVariables = uri.split("/", 3);
		logger.info("URI: "+uri);
		
		if(pathVariables != null && pathVariables.length > 1 && !pathVariables[1].equals("resources")) {
			String reducedURI = "/";
			final String langCode = pathVariables[1];
			if(isLocale(langCode)) {				
				request.setAttribute(StringUtil.LANG_ATTRIBUTE_NAME, langCode);
				reducedURI = uri.substring(3);				
			} else {
				reducedURI = uri;
				logger.info("URI ne sadrzi lang code. Koristimo podrazumevani.");
			}			
			RequestDispatcher dispatcher = request.getRequestDispatcher(reducedURI);
	        dispatcher.forward(request, response);
		} else {
			logger.warn("Uri ne sadrzi variable ili je null");
			filterChain.doFilter(request, response);
		}		
	}
	
	private boolean isLocale(String langCode) {
		boolean result = false;
		logger.info("proveravamo lang_code: "+langCode);
		if(StringUtil.notEmpty(langCode) && Pattern.matches("^[a-z]{2}]?", langCode)) {
			result = Arrays.asList(StringUtil.LANG_CODES).contains(langCode);			
		} else {
			result = false;
		}
		logger.info("rezultat provere: "+result);
		return result;
	}

}
