package org.sobakaisti.mvt.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialValidator {
	
	@Autowired
	private AccountManagerDao accountManagerDao;

	private Pattern pattern;
    private Matcher matcher;
    private String massage;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String USERNAME_PATTERN = "^[a-z0-9._-]{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,30}$";
       
    public void validateUserNameFormat(String username) {
        if(!matchPatternWithValue(USERNAME_PATTERN, username))
        	throw new BadCredentialsException("Invalid username!");        
    }
    public void validateEmailFormat(String email) {
        if(!matchPatternWithValue(EMAIL_PATTERN, email))
        	throw new BadCredentialsException("Invalid email address!");
    }
    public void validatePasswordFormat(String password) {
        if(!matchPatternWithValue(PASSWORD_PATTERN, password))
        	throw new BadCredentialsException("Invalid password.");
    }
    
//    check if requested field is empty
    public void checkIfInputsAreEmpty(String input){		
		if(input==null || input.trim().isEmpty())
			throw new BadCredentialsException("Polje ne sme biti prazno.");
	}
//    Check if entered e-mail or username already exist in DB	
    public void checkIfInputExists(String field, String value){
    	switch (field) {
		case "sbk_username":
			this.massage = "Korisničko ime je zauzeto";
			break;
		case "email":
			this.massage = "Nalog sa E-mail adresom već postoji";
			break;
		}
    	if(accountManagerDao.checkIfFieldExists(field, value))
    		throw new BadCredentialsException(massage+"  <a href='' class='err-login-link'>login?</a>");
    }
//    check if passed value and pattern matches
    private boolean matchPatternWithValue(String p, String v){
    	 pattern = Pattern.compile(p);
         matcher = pattern.matcher(v);
         return matcher.matches() ? true : false;        	 
    }    
}