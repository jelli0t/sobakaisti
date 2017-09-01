/**
 * 
 */
package org.sobakaisti.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jelles
 *
 */
public class StringUtil {

	public static final char[] UNFRIENDLY_CHARS = {'?', '&', '#', '!', '-', '%', '*', ':', ';', ',', '.', '+', '/', '@', '(', ')', '"'};
	public static final Map<Character, Character> cyrToAscii = initializeBasicCyrMap();
	
	// Pattern.matches(".*\\p{InCyrillic}.*", text)
	
	public static String makeSlugFromTitle(String title){
		title = title.trim().toLowerCase();
		title = removeUnfriendlyChars(title);
		title = title.replaceAll("\\s+", "-");		
		try {
			title = toASCII(title);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}
	
	public static String toASCII(String value) throws UnsupportedEncodingException{
		value = value.toLowerCase();
		byte bytes[] = value.getBytes("UTF-8");
		String asciiStringe = new String(bytes, "ISO-8859-1");
		System.out.println("ASCII title: "+asciiStringe);
		
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
	
	
	
	public static String generateUrlFriendlyString(String input) {
		Map<Character, Character> charsMap = cyrToAscii;
		input = input.toLowerCase();
		char[] output = new char[input.length()];
		for(int i=0; i < input.length(); i++) {
			char temp = input.charAt(i);
			output[i] = charsMap.containsKey(temp) ? charsMap.get(temp).charValue() : 0; 
		}
		System.out.println("output: "+ new String(output));
		return new String(output);
	}
	
	private static Map<Character, Character> initializeBasicCyrMap() {
		Map<Character, Character> basicCyr = new HashMap<Character, Character>(30);
		basicCyr.put('\u0430', 'a');
		basicCyr.put('\u0431', 'b');
		basicCyr.put('\u0432', 'v');
		basicCyr.put('\u0433', 'g');
		basicCyr.put('\u0434', 'd');
		basicCyr.put('\u0452', 'd');
		basicCyr.put('\u0435', 'e');		
		basicCyr.put('\u0436', 'z');
		basicCyr.put('\u0437', 'z');
		basicCyr.put('\u0438', 'i');
		basicCyr.put('\u0458', 'j');
		basicCyr.put('\u043A', 'k');
		basicCyr.put('\u043B', 'l');
		basicCyr.put('\u0459', 'l');
		basicCyr.put('\u043C', 'm');
		basicCyr.put('\u043D', 'n');
		basicCyr.put('\u045A', 'n');
		basicCyr.put('\u043E', 'o');
		basicCyr.put('\u043F', 'p');
		basicCyr.put('\u0440', 'r');
		basicCyr.put('\u0441', 's');
		basicCyr.put('\u0442', 't');
		basicCyr.put('\u045B', 'c');
		basicCyr.put('\u0443', 'u');
		basicCyr.put('\u0444', 'f');
		basicCyr.put('\u0445', 'h');		
		basicCyr.put('\u0446', 'c');
		basicCyr.put('\u0447', 'c');
		basicCyr.put('\u045F', 'd');
		basicCyr.put('\u0448', 's');
		return basicCyr;
	}
    
	
}