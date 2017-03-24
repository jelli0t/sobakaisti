/**
 * 
 */
package org.sobakaisti.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

/**
 * @author jelles
 *
 */
public class StringUtil {

	public static final char[] UNFRIENDLY_CHARS = {'?', '&', '#', '!', '-', '%', '*', ':', ';', ',', '.', '+', '/', '@', '(', ')', '"'};
	
	public static String makeSlugFromTitle(String title){
		title = title.trim().toLowerCase();
		title = removeUnfriendlyChars(title);
		title = title.replaceAll("\\s+", "-");
		title = toASCII(title);
		return title;
	}
	
	public static String toASCII(String value){
		value = value.toLowerCase();
		char[] chars = value.toCharArray();
		
		for(int i=0; i<chars.length; i++){
			switch (chars[i]) {
			case 'č':
				chars[i] = 'c';
				break;
			case 'ć':
				chars[i] = 'c';
				break;
			case 'š':
				chars[i] = 's';
				break;
			case 'ž':
				chars[i] = 'z';
				break;
			case 'đ':
				chars[i] = 'd';
				break;
			default:
				break;
			}			
		}
		final String asciiValue = new String(chars);		
		return asciiValue;
	}
	
	/**
	 *  Metoda uklanja karaktere nepredvidjene za url
	 *  @param sirov string za obradu
	 * */
	public static String removeUnfriendlyChars(String value){
		char[] chars = value.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean isFriendly;
		for(int i=0; i<chars.length; i++){
			isFriendly = true;
			for(int j=0; j<UNFRIENDLY_CHARS.length; j++){
				if(chars[i]==UNFRIENDLY_CHARS[j]){
					isFriendly = false;
					break;
				}				
			}			
			if(isFriendly)
				sb.append(chars[i]);
		}		
		return sb.toString();
	}
	
	
}