/**
 * 
 */
package org.sobakaisti.security;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			
//		try{
//			account = (Account) accountDetailsService.loadUserByUsername(principal);
//			validator.checkIfPasswordsMatches(credential, account.getPassword());
//			report.add(new StatusReport("OK", "Successfull login"));
//			token = new UsernamePasswordAuthenticationToken(principal, credential);
//			System.out.println("#2 principal: "+principal+" / credential: "+credential);
//			this.authenticate(token);
//		}catch (BadCredentialsException ex) {
//			report.add(new StatusReport("ERR", "#2 Please check the username and password and try again."));
//			return report;
//		}
//		return report;
//	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String principal = authentication.getPrincipal().toString();
		final String credential = authentication.getCredentials().toString();
		System.out.println("#1 principal: "+principal+" / credential: "+credential);
		/*	Validate if login fields are empty	*/
		try{
			validator.checkIfInputsAreEmpty(principal);
			validator.checkIfInputsAreEmpty(credential);
		}catch (BadCredentialsException ex) {
			throw new BadCredentialsException(ex.getMessage());
		}	
		/*	Validate format of input login values	*/
		try{
			if(principal.contains("@"))
				validator.validateEmailFormat(principal);
			else
				validator.validateUserNameFormat(principal);
			
			validator.validatePasswordFormat(credential);			
		}catch (BadCredentialsException ex) {
			throw new BadCredentialsException("Please check the username and password and try again.");
		}
		/*	Uzima account obj iz baze
		 * 	proverava da li se uneta i trenutna lozinka podudaraju
		 * pravi token koji ce da preda za security context
		 */
		try{
			account = (Account) accountDetailsService.loadUserByUsername(principal);			
			validator.checkIfPasswordsMatches(credential, account.getPassword());
			token = new UsernamePasswordAuthenticationToken(principal, credential, account.getAuthorities());
			
		}catch (BadCredentialsException ex) {
			throw new BadCredentialsException("#2 Please check the username and password and try again.");
		}
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
