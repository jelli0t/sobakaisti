package org.sobakaisti.util;

public final class TextUtil {
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