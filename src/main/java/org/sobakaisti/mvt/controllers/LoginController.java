/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.models.StatusReport;
import org.sobakaisti.mvt.service.impl.AccountValidationServiceImpl;
import org.sobakaisti.security.AccountAuthenticationProvider;
import org.sobakaisti.security.Authority.Role;
import org.sobakaisti.security.model.User;
import org.sobakaisti.security.service.UserService;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.MailMessage;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		if(enableInitAdminSignup & !model.containsAttribute("newUser"))
			model.addAttribute("newUser", new org.sobakaisti.security.model.User());
		
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
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String submitUserSignup(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
				      RedirectAttributes redirectAttributes) {
		System.out.println("new user: "+newUser.getUsername() + ", mail: "+newUser.getEmail());
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", result);
			redirectAttributes.addFlashAttribute("newUser", newUser);
			return "redirect:/login";
		}
		boolean exists = userService.checkIfUserExists(newUser.getUsername(), newUser.getEmail());
		if(exists) {
		//	result.rejectValue("email", "errors.signup.email", "Email address is already in use.");
			String errorMessage = messageSource.getMessage("signup.exception.user.exists", null, LocaleContextHolder.getLocale());
			redirectAttributes.addFlashAttribute("commitResult", new CommitResult(false, errorMessage));			
			return "redirect:/login";
		}	
		newUser = userService.initialAdministratorSignup(newUser);
		
		
		return "redirect:/sbk-admin";
	}
	
			
}
