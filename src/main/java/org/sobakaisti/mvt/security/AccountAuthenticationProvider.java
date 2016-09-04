/**
 * 
 */
package org.sobakaisti.mvt.security;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.LogInData;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.validation.UserCredentialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *	custom authentication provider for login to site
 */
@Service("authenticationProvider")
public class AccountAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserCredentialValidator validator;
	@Autowired
	@Qualifier("accountDetailsService")
	private UserDetailsService accountDetailsService;
	private UsernamePasswordAuthenticationToken token;
	private Account account;
	
	public List<StatusReport> preAuthenticate(LogInData loginData){
		List<StatusReport> report = new ArrayList<StatusReport>(1);
		
		final String principal = loginData.getPrincipal();
		final String credential = loginData.getCredential();
		final boolean remembered = loginData.isRemembered();
		System.out.println("#1 principal: "+principal+" / credential: "+credential);
		try{
			validator.checkIfInputsAreEmpty(principal);
			validator.checkIfInputsAreEmpty(credential);
		}catch (BadCredentialsException ex) {
			report.add(new StatusReport("ERR", "Polja ne smeju biti prazna."));
			return report;
		}	
		
		try{
			if(principal.contains("@"))
				validator.validateEmailFormat(principal);
			else
				validator.validateUserNameFormat(principal);
			validator.validatePasswordFormat(credential);			
		}catch (BadCredentialsException ex) {
			report.add(new StatusReport("ERR", "#1 Please check the username and password and try again."));
			return report;
		}
		
		try{
			account = (Account) accountDetailsService.loadUserByUsername(principal);
			validator.checkIfPasswordsMatches(credential, account.getPassword());
			report.add(new StatusReport("OK", "Successfull login"));
			token = new UsernamePasswordAuthenticationToken(principal, credential);
			System.out.println("#2 principal: "+principal+" / credential: "+credential);
			this.authenticate(token);
		}catch (BadCredentialsException ex) {
			report.add(new StatusReport("ERR", "#2 Please check the username and password and try again."));
			return report;
		}
		return report;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("authenticate()");
		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
