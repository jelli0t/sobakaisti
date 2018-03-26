package org.sobakaisti.util;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.i18n.LocaleContextHolder;


public final class PropertiesUtil {
	
	private static final String QUOTES_DIR_NAME = "quotes";	
	private static final String QUOTES_PROPERTIES_PREFIX = "quotes_";
	public static final String MANIFEST_QUOTES_BUNDLE_KEY = "quote.manifest.bundle";
	
  	public static PropertiesUtil quotes = new PropertiesUtil(QUOTES_PROPERTIES_PREFIX);
  	
	private Properties properties;

  	private PropertiesUtil(String propreties_prefix) {
		properties = new Properties();
		// TODO postavi u zaseban dir
		String fileName = QUOTES_DIR_NAME + TextUtil.FILE_SEPARATOR + propreties_prefix + getLocaleLanguage() + ".properties";
		try {			
			properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			//log.info("variables.properties file loaded successfully");
		} catch (Exception e) {
			//log.error(fileName + " file not found: ", e);
		}
 	}
  	
	public Properties getProperties() {
		return properties;
	}
	
	/**
	* Za zadati kljuc dohvata odgovarajucu vrednost
	* @param key
	* */
	public String valueOf(String key) {
		return getProperties().getProperty(key).trim();
	}
	
	/**
	 * Dohvata listu vrednosti za prosledjeni kljuc. Za kljuc dohvata 
	 * jedan string koji grupise reference na vise propery-a odvojenih karakterom: #
	 * @param key
	 * @return vraca podeljeni string kao listu
	 * */
	public List<String> getBundleValues(String key) {
		List<String> values = new ArrayList<>();
		String raw = getProperties().getProperty(key);
		if(TextUtil.notEmpty(raw)) {
			String[] valArray = raw.split(String.valueOf(TextUtil.HASH_CHAR));
			if(valArray != null && valArray.length > 0) {
				for(int i=0; i<valArray.length; i++)
					values.add(getProperties().getProperty(valArray[i]));
			}
		}			
		return values;
	}
	
	public String getLocaleLanguage() {
		Locale locale = LocaleContextHolder.getLocale();		
		return locale != null ? locale.getLanguage() : StringUtil.DEFAULT_LANG_CODE;
	}

	
}
