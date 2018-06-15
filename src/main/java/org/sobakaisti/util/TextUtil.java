package org.sobakaisti.util;

public final class TextUtil {
	/*
	 * Chars
	 * */
	public static final char HASH_CHAR = 0x23;
	public static final char SPACE_CHAR = 0x20;
	public static final char NULL_CHAR = 0x0;
	public static final char DOT_CHAR = 0x2e;
	public static final char SEMICOLON_CHAR = 0x3b;
	public static final char SLASH_CHAR = 0x2f;
	
	public static final String UTF8_CHAR_ENCODING = "UTF-8";
	
	public static final String EMPTY = "";
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");	
	/*
	 * ModelAttributes names
	 * */
	public static final String URL_BASIS_ATTR_NAME = "URL_BASIS";
	public static final String CONTACT_INDICATOR_ON_ATTR = "CONTACT_INDICATOR_ON";
	public static final String POST_ATTR_NAME = "post";
	public static final String POSTS_ATTR_NAME = "posts";
	public static final String RELATED_POSTS_ATTR_NAME = "relatedPosts";
	
	/**
	* Default private constrictor prevents class to be instantiated
	* */
	private TextUtil() {}
	
	/**
	 * Check if string is not empty. 
	 * Check if input string is not null and checks length of input
	 * */
	public static boolean notEmpty(String input) {	
		return input != null && input.length() > 0;		
	}
	
	/**
	 * Check if input string is null or equal 0.
	 * @param input
	 * */
	public static boolean isEmpty(String input) {
		return input == null || input.length() == 0;
	}
}
