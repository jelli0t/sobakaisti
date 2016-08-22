package org.sobakaisti.mvt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/")
	public String displayHome(){
		return "home";
	}
	
	@RequestMapping(value="/movement", method=RequestMethod.GET )
	public String showMovementHome(){
		return "movement";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET )
	public String showLogin(){
		return "login";
	}
}
