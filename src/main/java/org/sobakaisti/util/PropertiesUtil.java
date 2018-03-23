package org.sobakaisti.util;

public final class PropertiesUtil {
  
  private static final String QUOTES_PROPERTIES_PREFIX = "quotes_";

  public static PropertiesUtil quotesProperties = new PropertiesUtil(QUOTES_PROPERTIES_PREFIX);

  private PropertiesUtil(String propreties_prefix) {
    properties = new Properties();
    String fileName = propreties_prefix + ".properties";
		try {			
			properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
			log.info("variables.properties file loaded successfully");
		} catch (Exception e) {
			log.error(fileName + " file not found: ", e);
		}
  }
}
