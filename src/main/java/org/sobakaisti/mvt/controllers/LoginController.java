/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;
import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.LogInData;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.security.AccountAuthenticationProvider;
import org.sobakaisti.mvt.service.impl.AccountValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jelles
 *	Controlls evri request which starts with /login
 */
@Controller
public class LoginController {

	private Account account;
	
	@Autowired
	private AccountValidationServiceImpl accountValidationServiceImpl;
	@Autowired
	@Qualifier("authenticationProvider")
	private AccountAuthenticationProvider authenticationProvider;
	@Autowired
	private UserDetailsService accountDetailsService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegisterPage(Model model){
		account = new Account();
		model.addAttribute("newAccount", account);
		return "register";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST,
					consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<StatusReport> createNewAccount(@RequestBody Account newAccount){		
		return accountValidationServiceImpl.validateRegistration(newAccount);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping(value="/do.login", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<StatusReport> logIn(@RequestBody final LogInData loginData){
		return authenticationProvider.preAuthenticate(loginData);
	}
		
}
