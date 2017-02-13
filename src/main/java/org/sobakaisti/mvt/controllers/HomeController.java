package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value="/")
	public String displayHome(){
		System.out.println("Home controller user: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return "intro";  // home
	}
	
	@RequestMapping(value="/movement", method=RequestMethod.GET )
	public String showMovementHome(){
		return "mvt_intro";
	}	
	
	@RequestMapping(value="/load_background", method=RequestMethod.GET)
	@ResponseBody
	public List<String> organizeBackground(@RequestParam("width") int width, 
										@RequestParam("height") int height, 
										@RequestParam("charWidth") double charWidth, Model model){
		List<String> rows = articleService.getRowsFromArticleWithDimension(width, height, charWidth);
				
		return rows;
	}
}