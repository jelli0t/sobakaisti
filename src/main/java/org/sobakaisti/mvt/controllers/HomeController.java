package org.sobakaisti.mvt.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.AuthorService;
import org.sobakaisti.util.PropertiesUtil;
import org.sobakaisti.util.Socials;
import org.sobakaisti.util.TextUtil;
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
		model.addAttribute("quotes", authorService.getAuthorsManifestQuotes());
		return "authors";
	}
	
	@RequestMapping(value="/authors/bio/{authorSlug}", method=RequestMethod.GET)
	public String showAuthorBio(@PathVariable String authorSlug, Model model) {
		if(TextUtil.notEmpty(authorSlug)) {
			model.addAttribute("author", authorService.findBySlug(authorSlug));			
			model.addAttribute("socials", PropertiesUtil.socials.findSocialNetworkProfilesForAuthor(authorSlug));			
		}
		return "commons/fragments :: authorBioFragment";
	}
	
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String showContactPage(Model model) {
		model.addAttribute(TextUtil.CONTACT_INDICATOR_ON_ATTR, true);
		model.addAttribute("authors", authorService.findAll());
		return "contact";
	}
	
	@RequestMapping(value="/contact/by/{author}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Author> getAuthorToContact(@PathVariable String author) {
		Author contactAuthor = null;
		if(TextUtil.notEmpty(authorSlug)) {
			contactAuthor = authorService.findBySlug(author);
			return new ResponseEntity<Author>(contactAuthor, HttpStatus.OK);
		} else {
			return new ResponseEntity<Author>(null, HttpStatus.SERVICE_UNAVAILABLE);
		}	
	}
}
