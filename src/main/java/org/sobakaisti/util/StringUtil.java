/**
 * 
 */
package org.sobakaisti.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author jelles
 *
 */
public class StringUtil {

	public static final Set<Character> SPECIAL_CHARS = initializeSpecialCharSet();
	public static final char[] UNFRIENDLY_CHARS = {'?', '&', '#', '!', '-', '%', '*', ':', ';', ',', '.', '+', '/', '@', '(', ')', '"'};
	public static final String LANG_ATTRIBUTE_NAME = "lang";
	public static final String LANG_CODE_RS = "rs";
	public static final String LANG_CODE_EN = "en";
	public static final String LANG_CODE_ES = "es";
	public static final String LANG_CODE_DE = "de";
	public static final String LANG_CODE_IT = "it";
	public static final String LANG_CODE_FR = "fr";
	public static final String LANG_CODE_RU = "ru";
	public static final String LANG_CODE_CH = "ch";
	public static final String[] LANG_CODES = {LANG_CODE_RS, LANG_CODE_EN, LANG_CODE_ES, LANG_CODE_DE, LANG_CODE_IT,
			LANG_CODE_FR, LANG_CODE_RU, LANG_CODE_CH};
	/* set default lang_code */
	public static final String DEFAULT_LANG_CODE = LANG_CODE_RS;
	
	public static final char SLASH_CHAR = '\u002F';
	
	private static final String FILESYS_PATH_PREFIX = "file:";
	
	// Pattern.matches(".*\\p{InCyrillic}.*", text)
	@Deprecated
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
	
	@Deprecated
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
	@Deprecated
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
	
	/**
	 * Uzima sirov string i od njega pravi URL friendly strng - slug
	 * */
	public static String makeSlug(String input){
		input = input.trim().toLowerCase();		
		boolean isCyrilic = Pattern.matches(".*\\p{InCyrillic}.*", input);
		return generateUrlFriendlyString(input, isCyrilic);
	}
	
	/**
	 * Generise URL friendly string - slug
	 * */
	public static String generateUrlFriendlyString(String input, boolean isCyrilic) {
		final Map<Character, Character> charSubstituteMap = isCyrilic ? initializeBasicCyrMap() : initializeSerbinLatinMap();
		StringBuilder output = new StringBuilder();		
		for(int i=0; i < input.length(); i++) {
			char temp = input.charAt(i);
			if(SPECIAL_CHARS.contains(temp))
				continue;			
			output.append(charSubstituteMap.containsKey(temp) ? charSubstituteMap.get(temp).charValue() : temp); 
		}
		System.out.println("output: "+ new String(output));
		return output.toString();
	}
	
	
	/**
	 * Izdvaja ektenziju za prosledjeno puno ime datoteke
	 * @param filename	puno ime datoteke
	 * */
	public static String extractFilenameExtension(String filename) {
		int dotIndex = filename.lastIndexOf('\u002E');
		return filename.substring(dotIndex);
	}
	
	/**
	 * Vraca naziv datoteke bez ekstenzije
	 * */
	public static String trimExtensionFromFilename(String filename) {
		return filename.substring(0, filename.lastIndexOf('\u002E'));
	}
	
	public static String makeUserFriendlyTitleFromFilename(String filename) {
		String extensionless = trimExtensionFromFilename(filename);
		extensionless = generateUrlFriendlyString(extensionless, false);
		// capitalize first letter
		extensionless = extensionless.substring(0, 1).toUpperCase() + extensionless.substring(1);
		// substitute all - to spaces
		return extensionless.replaceAll("-", " ");
	}
	
	/**
	 * Inicijalizuje mapu za konverziju cirilicnih karaktera u ASCII
	 * */
	private static Map<Character, Character> initializeBasicCyrMap() {
		Map<Character, Character> basicCyr = new HashMap<Character, Character>(31);
		basicCyr.put('\u0020', '\u002D');	// space to -
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
    
	/**
	 * Inicijalizuje mapu za zamenu specijalnih karaktera
	 * */
	private static Set<Character> initializeSpecialCharSet() {
		Set<Character> spetialsChars = new HashSet<Character>();		
		spetialsChars.add('\u003A');	// :
		spetialsChars.add('\u003B');	// ;
		spetialsChars.add('\u003C');	// <
		spetialsChars.add('\u003D');	// =
		spetialsChars.add('\u003E');	// >
		spetialsChars.add('\u003F');	// ?		
		spetialsChars.add('\u0040');	// @
		spetialsChars.add('\u0021');	// !
		spetialsChars.add('\u0022');	// "
		spetialsChars.add('\u0023');	// #
		spetialsChars.add('\u0024');	// $		
		spetialsChars.add('\u0025');	// %		
		spetialsChars.add('\u0026');	// &
		spetialsChars.add('\'');	// '
		spetialsChars.add('\u0028');	// (
		spetialsChars.add('\u0029');	// )		
		spetialsChars.add('\u002A');	// *		
		spetialsChars.add('\u002B');	// +
		spetialsChars.add('\u002C');	// ,
//		spetialsChars.add('\u002D');	// -
		spetialsChars.add('\u002E');	// .		
		spetialsChars.add('\u002F');	// /			
		spetialsChars.add('\u007B');	// {		
		spetialsChars.add('\u007C');	// |
		spetialsChars.add('\u007D');	// }
		spetialsChars.add('\u007E');	// ~		
		spetialsChars.add('\u005B');	// [		
		spetialsChars.add('\\');	// \
		spetialsChars.add('\u005D');	// 		
		spetialsChars.add('\u005E');	// ^
		spetialsChars.add('\u0060');	// `		
		return spetialsChars;
	}
	
	/**
	 * Inicijalizuje mapu za konverziju srpskih latinicnih karaktera
	 * */
	private static Map<Character, Character> initializeSerbinLatinMap() {
		Map<Character, Character> serbianChars = new HashMap<Character, Character>();
		serbianChars.put('\u0020', '\u002D');	// ' ' to '-'
		serbianChars.put('\u005F', '\u002D');	// '_' to '-'
		serbianChars.put('\u0107', 'c');
		serbianChars.put('\u010D', 'c');
		serbianChars.put('\u0111', 'd');
		serbianChars.put('\u0161', 's');
		serbianChars.put('\u017E', 'z');		
		return serbianChars;
	}
	
	
	public static boolean notEmpty(String string) {	
		return (string != null && string.length() > 0);		
	}
	
}