/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;
import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.service.impl.AccountValidationServiceImpl;
import org.sobakaisti.security.AccountAuthenticationProvider;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jelles
 *	Controlls every request which starts with /login
 */
@Controller
public class LoginController {

	private Account account;
	
	@Autowired
	private AccountValidationServiceImpl accountValidationServiceImpl;
	@Autowired
	@Qualifier("authenticationProvider")
	private AccountAuthenticationProvider authenticationProvider;
	
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public String showLogin(@RequestParam(value="errorCode", required=false) String errorCode, Model model){
		
		if(TextUtil.notEmpty(errorCode)) {
			String errorMessage = "";
			model.addAttribute("commitResult", new CommitResult(false, errorCode));
			
		}
		
		
		return "login";
	}
	
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
	
			
}