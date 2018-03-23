package org.sobakaisti.util;

public final class PropertiesUtil {
	
	private static final String QUOTES_PROPERTIES_PREFIX = "quotes_";
  	public static PropertiesUtil quotes = new PropertiesUtil(QUOTES_PROPERTIES_PREFIX);
	
	private Properties properties;
	private static String fsep = System.getProperty("file.separator");

  	private PropertiesUtil(String propreties_prefix) {
		properties = new Properties();
		// TODO postavi u zaseban dir
		String fileName = propreties_prefix + getLocaleLanguage() + ".properties";
		try {			
			properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			log.info("variables.properties file loaded successfully");
		} catch (Exception e) {
			log.error(fileName + " file not found: ", e);
		}
 	}
	
	public Properties getProperties() {
		return properties;
	}
	
	
	
	public String getLocaleLanguage() {
		Locale locale = LocaleContextHolder.getLocale();		
		return locale != null ? locale.getLanguage() : StringUtil.DEFAULT_LANG_CODE;
	}
}
