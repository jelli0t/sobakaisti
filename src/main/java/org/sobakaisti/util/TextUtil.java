package org.sobakaisti.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public final class TextUtil {
	/*
	 * signs Chars
	 * */
	public static final char SPACE_CHAR = 0x20;
	public static final char NULL_CHAR = 0x0;
	public static final char DOT_CHAR = 0x2e;
	public static final char COMMA_CHAR = 0x2c;
	public static final char SEMICOLON_CHAR = 0x3b;
	public static final char SLASH_CHAR = 0x2f;
	public static final char BACKSLASH_CHAR = 0x5c;
	public static final char DOUBLE_QUOTE = 0x22;
	public static final char HASH_CHAR = 0x23;
	public static final char PERCENT_CHAR = 0x25;
	public static final char AMPERSAND_CHAR = 0x26;
	public static final char COLON_CHAR = 0x3A;
	public static final char LESS_THEN = 0x3c;
	public static final char GREATER_THEN = 0x3e;
	public static final char QUESTION_MARK_CHAR = 0x3f;
	public static final char EQUALS_SIGN_CHAR = 0x3d;
	public static final char AT_MARK_CHAR = 0x40;
	public static final char HYPHEN_CHAR = 0x2d;
	public static final char CARET_CHAR = 0x5e;
	public static final char GRAVE_CHAR = 0x60;
	public static final char LEFT_CURLY_BRACKET = 0x7b;
	public static final char RIGHT_CURLY_BRACKET = 0x7d;
	public static final char LEFT_SQUARE_BRACKET = 0x5b;
	public static final char RIGHT_SQUARE_BRACKET = 0x5d;
	public static final char PIPE_CHAR = 0x7c;
	public static final char TILDE_CHAR = 0x7e;	
	/*
	 * Serbian chars latin
	 * */
	public static final char SH_UPPER = 0x0160;		// Š
	public static final char SH_LOWER = 0x0161;		// š
	public static final char DJ_UPPER = 0x0110;		// Đ
	public static final char DJ_LOWER = 0x0111;		// đ
	public static final char TJ_UPPER = 0x0106;		// Ć
	public static final char TJ_LOWER = 0x0107;		// ć
	public static final char CH_UPPER = 0x010c;		// Ć
	public static final char CH_LOWER = 0x010d;		// ć
	public static final char ZH_UPPER = 0x017d;		// Ž
	public static final char ZH_LOWER = 0x017e;		// ž
	public static final char DZ_UPPER = 0x01c5;		// Dž
	public static final char DZ_LOWER = 0x01c6;		// dž
	public static final char LJ_UPPER = 0x01c8;		// Lj
	public static final char LJ_LOWER = 0x01c9;		// lj
	public static final char NJ_UPPER = 0x01cb;		// Lj
	public static final char NJ_LOWER = 0x01cc;		// lj
	
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
	 * Unsafe URL characters
	 * */
	public static final Character[] URL_UNSAFE_CHARS_ARRAY = {LEFT_CURLY_BRACKET, RIGHT_CURLY_BRACKET, 
					PIPE_CHAR,BACKSLASH_CHAR, CARET_CHAR, TILDE_CHAR, LEFT_SQUARE_BRACKET, RIGHT_SQUARE_BRACKET, 
					GRAVE_CHAR, LESS_THEN, GREATER_THEN, HASH_CHAR, PERCENT_CHAR, SPACE_CHAR};
	public static final List<Character> URL_UNSAFE_CHARS_LIST = Arrays.asList(URL_UNSAFE_CHARS_ARRAY);	
	/**
	 * Reserved URL characters
	 * */
	public static final Character[] URL_RESERVED_CHARS_ARRAY = {SEMICOLON_CHAR, SLASH_CHAR, QUESTION_MARK_CHAR, 
					COLON_CHAR, AT_MARK_CHAR, EQUALS_SIGN_CHAR, AMPERSAND_CHAR};
	public static final List<Character> URL_RESERVED_CHARS_LIST = Arrays.asList(URL_RESERVED_CHARS_ARRAY);
		

	public static final List<Character> SLUG_UNFRIENDLY_SIGNS_LIST = new ArrayList<Character>() {{
		addAll(URL_UNSAFE_CHARS_LIST);
		addAll(URL_RESERVED_CHARS_LIST);
	}};
	
	public static final Map<Character, String> SRB_LATIN_SUBSTITUTION_MAP = new HashMap<Character, String>() {{
		put(CH_UPPER, "C");
		put(CH_LOWER, "c");
		put(TJ_UPPER, "C");
		put(TJ_LOWER, "c");
		put(SH_UPPER, "S");
		put(SH_LOWER, "s");
		put(DJ_UPPER, "Dj");
		put(DJ_LOWER, "dj");
		put(ZH_UPPER, "Z");
		put(ZH_LOWER, "z");
		put(DZ_UPPER, "Dz");
		put(DZ_LOWER, "dz");
		put(LJ_UPPER, "Lj");
		put(LJ_LOWER, "lj");
		put(NJ_UPPER, "Nj");
		put(NJ_LOWER, "nj");
	}};
	
	public static final Map<Character, String> CYR_TO_ASCII_SUBSTITUTION_MAP = new HashMap<Character, String>() {{
		/* Uppercase */
		put((char) 0xd090, "A");	// CYRILLIC CAPITAL LETTER A
		put((char) 0xd091, "B");	// CYRILLIC CAPITAL LETTER BE
		put((char) 0xd092, "V");
		put((char) 0xd093, "G");
		put((char) 0xd094, "D");	// CYRILLIC CAPITAL LETTER De
		put((char) 0xd082, "Dj");	// CYRILLIC CAPITAL LETTER DJE
		put((char) 0xd085, "E");
		put((char) 0xd096, "Z");	// CYRILLIC CAPITAL LETTER ZHE		
		put((char) 0xd097, "Z");	// CYRILLIC CAPITAL LETTER ZE
		put((char) 0xd098, "I");		
		put((char) 0xd088, "J");
		put((char) 0xd09a, "K");
		put((char) 0xd09b, "L");	// CYRILLIC CAPITAL LETTER EL
		put((char) 0xd089, "Lj");	// CYRILLIC CAPITAL LETTER LJE
		put((char) 0xd09c, "M");	// CYRILLIC CAPITAL LETTER EM
		put((char) 0xd09d, "N");	// CYRILLIC CAPITAL LETTER EN		
		put((char) 0xd08a, "Nj");
		put((char) 0xd09e, "O");
		put((char) 0xd09f, "P");
		put((char) 0xd0a0, "R");	// CYRILLIC CAPITAL LETTER EL		
		put((char) 0xd0a1, "S");	// CYRILLIC CAPITAL LETTER LJE
		put((char) 0xd0a2, "T");	// CYRILLIC CAPITAL LETTER EM
		put((char) 0xd08b, "C");	// CYRILLIC CAPITAL LETTER TSHE		
		put((char) 0xd0a3, "U");	// CYRILLIC CAPITAL LETTER EN		
		put((char) 0xd0a4, "F");	// CYRILLIC CAPITAL LETTER EF		
		put((char) 0xd0a5, "H");	// CYRILLIC CAPITAL LETTER HA
		put((char) 0xd0a6, "C");	// CYRILLIC CAPITAL LETTER TSE
		put((char) 0xd0a7, "C");	// CYRILLIC CAPITAL LETTER CHE		
		put((char) 0xd08f, "Dz");	// CYRILLIC CAPITAL LETTER DZHE
		put((char) 0xd0a8, "S");	// CYRILLIC CAPITAL LETTER SHA	
		/* Lowercase */
		put((char) 0xd0b0, "a");	// CYRILLIC SMALL LETTER A
		put((char) 0xd0b1, "b");	// CYRILLIC SMALL LETTER BE
		put((char) 0xd0b2, "v");
		put((char) 0xd0b3, "g");
		put((char) 0xd0b4, "d");	// CYRILLIC SMALL LETTER De
		put((char) 0xd192, "dj");	// CYRILLIC SMALL LETTER DJE
		put((char) 0xd0b5, "e");
		put((char) 0xd0b6, "z");	// CYRILLIC SMALL LETTER ZHE		
		put((char) 0xd0b7, "z");	// CYRILLIC SMALL LETTER ZE		
		put((char) 0xd0b8, "i");
		put((char) 0xd198, "j");
		put((char) 0xd0ba, "k");
		put((char) 0xd0bb, "l");	// CYRILLIC SMALL LETTER EL		
		put((char) 0xd199, "lj");	// CYRILLIC SMALL LETTER LJE
		put((char) 0xd0bc, "m");	// CYRILLIC SMALL LETTER EM
		put((char) 0xd0bd, "n");	// CYRILLIC SMALL LETTER EN		
		put((char) 0xd19a, "nj");		
		put((char) 0xd0be, "o");
		put((char) 0xd0bf, "p");
		put((char) 0xd180, "r");	// CYRILLIC SMALL LETTER ER		
		put((char) 0xd181, "s");	// CYRILLIC SMALL LETTER ES
		put((char) 0xd182, "t");	// CYRILLIC SMALL LETTER T
		put((char) 0xd19b, "c");	// CYRILLIC SMALL LETTER TSHE		
		put((char) 0xd183, "u");	// CYRILLIC SMALL LETTER U		
		put((char) 0xd184, "f");	// CYRILLIC SMALL LETTER EF		
		put((char) 0xd185, "h");	// CYRILLIC SMALL LETTER HA
		put((char) 0xd186, "c");	// CYRILLIC SMALL LETTER TSE
		put((char) 0xd187, "c");	// CYRILLIC SMALL LETTER CHE		
		put((char) 0xd19f, "dz");	// CYRILLIC SMALL LETTER DZHE
		put((char) 0xd188, "s");
	}};
	
	
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
	
	
	public String makeSlug(String input) {
		if(notEmpty(input)) {
			return input.chars()
					.mapToObj(c -> this.adjustCharForSlug((char) c))
					.collect(Collectors.joining(EMPTY));
		}
		return null;
	}
	
	private String adjustCharForSlug(char input) {
		String result = EMPTY;
		final char raw = Character.toLowerCase(input);
		/* AKo je razmak menjaj ga za '-' */
		if(raw == SPACE_CHAR)
			result = String.valueOf(HYPHEN_CHAR);
		/* Ako je cyrillic... */
		else if(Character.UnicodeBlock.of(raw).equals(Character.UnicodeBlock.CYRILLIC))
			result = CYR_TO_ASCII_SUBSTITUTION_MAP.containsKey(raw) 
					? CYR_TO_ASCII_SUBSTITUTION_MAP.get(raw) : String.valueOf(raw); 
		/* Ako je karakter latinicni sa kvacicama... */
		else if(SRB_LATIN_SUBSTITUTION_MAP.containsKey(raw))
			result = SRB_LATIN_SUBSTITUTION_MAP.get(raw);
		/* Ako je specijalni karakter menjaj ga za '-' */
		else if(SLUG_UNFRIENDLY_SIGNS_LIST.stream().anyMatch(s -> s.charValue() == raw))
			result = EMPTY;	        
		return result;
	}
}
