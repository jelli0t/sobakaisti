/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.Locale;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/manifesto")
public class ManifestoController {
			
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthorDao authorDao;
		
	public static final String MANIFESTO_SLUG = "manifesto";
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayManifestoPage(Model model){
		Locale locale = LocaleContextHolder.getLocale();
		
		model.addAttribute("authors", authorDao.getAllAuthors());
		model.addAttribute("article", articleService.getArticleBySlug(MANIFESTO_SLUG, locale.getLanguage()));
		return "manifesto";
	}
	
}
