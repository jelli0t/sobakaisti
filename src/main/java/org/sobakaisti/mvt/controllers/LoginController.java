/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;
import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.service.impl.AccountValidationServiceImpl;
import org.sobakaisti.security.AccountAuthenticationProvider;
import org.sobakaisti.security.service.UserService;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	private UserService userService;
	
	@Autowired
	private AccountValidationServiceImpl accountValidationServiceImpl;
	@Autowired
	@Qualifier("authenticationProvider")
	private AccountAuthenticationProvider authenticationProvider;
		
	@Autowired
	private MessageSource messageSource;
	
	private static final String ENABLE_INIT_ADMIN_SINGUP_FLAG = "ENABLE_INIT_ADMIN_SINGUP";
	
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public String showLogin(@RequestParam(value="errorCode", required=false) String errorCode, Model model) {
		
		boolean enableInitAdminSignup = !userService.haveUsersAtAll();
		model.addAttribute(ENABLE_INIT_ADMIN_SINGUP_FLAG, enableInitAdminSignup);
		
		if(TextUtil.notEmpty(errorCode)) {
			String errorMessage = messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
			model.addAttribute("commitResult", new CommitResult(false, errorMessage));			
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