package org.sobakaisti.mvt.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/")
	public String displayHome(){
		System.out.println("Ho,e controller user: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return "home";
	}
	
	@RequestMapping(value="/movement", method=RequestMethod.GET )
	public String showMovementHome(){
		return "movement";
	}	
}