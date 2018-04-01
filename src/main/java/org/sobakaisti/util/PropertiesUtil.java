package org.sobakaisti.util;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.i18n.LocaleContextHolder;


public final class PropertiesUtil {
	/**
	 * Social networks icons paths
	 * */
	public static final String FACEBOOK_ICON_PATH = "/resources/img/social/facebook-mono.svg";
	public static final String TWITTER_ICON_PATH = "/resources/img/social/twitter-mono.svg";
	public static final String INSTAGRAM_ICON_PATH = "/resources/img/social/instagram-mono.svg";
	public static final String YOUTUBE_ICON_PATH = "/resources/img/social/youtube-play-mono.svg";
	public static final String TUMBLR_ICON_PATH = "/resources/img/social/tumblr-mono.svg";
	public static final String SOUNDCLOUD_ICON_PATH = "/resources/img/social/soundcloud-mono.svg";
	public static final String BANDCAMP_ICON_PATH = "/resources/img/social/bandcamp-mono.svg";
	public static final String FLICKR_ICON_PATH = "/resources/img/social/flickr-mono.svg";
	
	/**
	 * Properties file related
	 * */
	private static final String QUOTES_DIR_NAME = "quotes";	
	private static final String QUOTES_PROPERTIES_PREFIX = "quotes_";
	public static final String MANIFEST_QUOTES_BUNDLE_KEY = "quote.manifest.bundle";
	
	private static final String SOCIALS_PROPERTIES_FILE_PREFIX = "socials";
	
	
  	public static PropertiesUtil quotes = new PropertiesUtil(QUOTES_DIR_NAME + TextUtil.FILE_SEPARATOR + QUOTES_PROPERTIES_PREFIX, true);
  	public static PropertiesUtil socials = new PropertiesUtil(SOCIALS_PROPERTIES_FILE_PREFIX, false);
  	
	private Properties properties;

  	private PropertiesUtil(String path, boolean langAware) {
		properties = new Properties();
		String lang = langAware ? getLocaleLanguage() : TextUtil.EMPTY;
		String fileName = path + lang + ".properties";
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
		String value = getProperties().getProperty(key);
		return TextUtil.notEmpty(value) ? value.trim() : TextUtil.EMPTY;
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
	
	/**
	 * Za prosledjen slug autora dovata podatke o njegovim drustvenim mrezama.
	 * Podaci su smesteni u socials.properties file-u. Svaki red u fileu ima kljuc
	 * u formatu <author-slug>.<socialnetwork-name> = <username>;<url>
	 * 
	 * @param authorSlug
	 * */
	public List<Socials> findSocialNetworkProfilesForAuthor(String authorSlug) {
		List<Socials> socials = new ArrayList<Socials>();
		for(Socials social : Arrays.asList(Socials.values())) {
			String key = authorSlug + TextUtil.DOT_CHAR + social.getName().toLowerCase();
			String raw = valueOf(key);
			if(TextUtil.notEmpty(raw)) {
				/*
				 * Split raw value on params and set params to Social object fill it into list
				 * */
				String[] params = raw.split(String.valueOf(TextUtil.SEMICOLON_CHAR));
				if(params != null && params.length == 2) {					
					social.setUsername(params[0]);
					social.setUrl(params[1]);
					socials.add(social);
					System.out.println(social);
				}
			}			
		} 
		
//		EnumSet.allOf(Socials.class).forEach(social -> {
//			String key = authorSlug + TextUtil.DOT_CHAR + social.getName().toLowerCase();
//			String raw = valueOf(key);
//			if(TextUtil.notEmpty(raw)) {
//				/*
//				 * Split raw value on params and set params to Social object fill it into list
//				 * */
//				String[] params = raw.split(String.valueOf(TextUtil.SEMICOLON_CHAR));
//				if(params != null && params.length == 2) {					
//					social.setUsername(params[0]);
//					social.setUrl(params[1]);
//					socials.add(social);
//					System.out.println(social);
//				}
//			}			
//		});
		return socials;
	}
	
	public String getLocaleLanguage() {
		Locale locale = LocaleContextHolder.getLocale();		
		return locale != null ? locale.getLanguage() : StringUtil.DEFAULT_LANG_CODE;
	}

	
}
