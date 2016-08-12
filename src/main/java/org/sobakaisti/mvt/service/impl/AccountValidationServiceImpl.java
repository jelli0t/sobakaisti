package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.service.AccountManagerService;
import org.sobakaisti.mvt.validation.UserCredentialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationServiceImpl {
	
	@Autowired
	private UserCredentialValidator validator;
	@Autowired
	private AccountManagerService accountManagerServiceImpl;

	private List<StatusReport> reports;
	
	public List<StatusReport> validateRegistration(Account account){
		reports = new ArrayList<StatusReport>(3);

		reports.add(0, validateUsername(account.getUsername()));
		reports.add(1, validateEmail(account.getEmail()));
		reports.add(2, validatePassword(account.getPassword()));
		
		int i=0;
		
		for(StatusReport sr : reports){
			if(sr.getStatus().equals("ERR"))
				i++;
		}
		if(i==0){	
//			if no errors then perform account creation
			accountManagerServiceImpl.createAccount(account);
		}
		
		return reports;
	}
	
	
	public StatusReport validateEmail(String email){		
		try{
			validator.checkIfInputsAreEmpty(email);
			validator.validateEmailFormat(email);
			validator.checkIfInputExists("email", email);
			return new StatusReport("OK");
		}catch (BadCredentialsException ex) {
			return new StatusReport("ERR",ex.getMessage());
		}
	}
	public StatusReport validateUsername(String username){		
		try{
			validator.checkIfInputsAreEmpty(username);
			validator.validateUserNameFormat(username);
			validator.checkIfInputExists("sbk_username", username);
			return new StatusReport("OK");
		}catch (BadCredentialsException ex) {
			return new StatusReport("ERR",ex.getMessage());
		}
	}
	public StatusReport validatePassword(String password){		
		try{
			validator.checkIfInputsAreEmpty(password);
			validator.validatePasswordFormat(password);
			return new StatusReport("OK");
		}catch (BadCredentialsException ex) {
			return new StatusReport("ERR",ex.getMessage());
		}
	}
	
}
