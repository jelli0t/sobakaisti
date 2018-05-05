/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
		model.addAttribute(PostService.MANIFESTO_INDICATOR_ON_ATTR, true);
		model.addAttribute("article", articleService.findBySlug(MANIFESTO_SLUG));		
		model.addAttribute("authorsForSignatures", authorDao.getAllAuthors());
		return "manifesto";
	}
	
}
