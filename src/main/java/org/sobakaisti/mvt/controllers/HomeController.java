package org.sobakaisti.mvt.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value="/")
	public String displayHome(){		
		return "intro";  // home
	}
	
	@RequestMapping(value="/movement", method=RequestMethod.GET )
	public String showMovementHome() {
		return "mvt_intro";
	}
	
	@RequestMapping(value="/movement/load_background", method=RequestMethod.GET)
	public String loadIntroBackground(@RequestParam("width") int width, 
										@RequestParam("height") int height, 
										@RequestParam("charWidth") double charWidth, Model model) {
		String lang = articleService.getPostLanguage();
		List<String> rows = articleService.getRowsFromArticleWithDimension(width, height, charWidth, lang);
		model.addAttribute("backgroundLines", rows);
		
		return "commons/fragments :: introBackground";
	}
	
	
	@RequestMapping(value="/movement/sitemap", method=RequestMethod.GET )
	public String showSiteMap() {
		return "sitemap";
	}
	
	
	@RequestMapping(value="/authors", method=RequestMethod.GET)
	public String showAuthorsHome(Model model) {
		model.addAttribute("authors", authorService.findAll());
		return "authors";
	}
}