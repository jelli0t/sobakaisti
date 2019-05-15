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
	public static final String BLANKO = String.valueOf(SPACE_CHAR);
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");	
	/*
	 * ModelAttributes names
	 * */
	public static final String JS_BTTN_ON_ATTR_NAME = "JS_BTTN_ON";
	public static final String JS_BTTN_CLASS_ATTR_NAME = "JS_BTTN_CLASS";
	public static final String URL_BASIS_ATTR_NAME = "URL_BASIS";
	public static final String CONTACT_INDICATOR_ON_ATTR = "CONTACT_INDICATOR_ON";
	public static final String POST_ATTR_NAME = "post";
	public static final String POSTS_ATTR_NAME = "posts";
	public static final String RELATED_POSTS_ATTR_NAME = "relatedPosts";
	/**
	 * measuring units
	 * */
	public static final String MEGABYTE_MEASURE_UNIT = "MB";
	public static final String KILOBYTE_MEASURE_UNIT = "KB";
	/*
	 * REGEX
	 * */
	public static final String HTML_TAG_REGEXP = "^<[^>]*>$";
		
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
	
	
	public static String convertBytesToReadableSize(long size) {
		double kbSize = size / 1024;
		double mbSize = kbSize / 1024;
		return mbSize < 1 ? String.format( "%.2f", kbSize ) + SPACE_CHAR + KILOBYTE_MEASURE_UNIT 
				: String.format( "%.2f", mbSize ) + SPACE_CHAR + MEGABYTE_MEASURE_UNIT;
	}
}
