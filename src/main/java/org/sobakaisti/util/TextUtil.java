package org.sobakaisti.util;

public final class TextUtil {
	
	/**
	 * Check if string is not empty
	 * */
	public static boolean notEmpty(String string) {	
		return (string != null && string.length() > 0);		
	}
	
	/**
	 * Check if input string is null or equal 0.
	 * @param input
	 * */
	public static boolean isEmpty(String input) {
		return input == null || input.length() == 0;
	}
}
