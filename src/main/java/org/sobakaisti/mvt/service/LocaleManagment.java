package org.sobakaisti.mvt.service;

import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author jelles
 *
 */
public class LocaleManagment {
	
	private Locale locale;

	public Locale getLocale() {
		this.locale = LocaleContextHolder.getLocale();
		return locale;
	}
	public void setLocale(String language) {
		this.locale = new Locale(language);
		LocaleContextHolder.setLocale(locale);
	}	
}