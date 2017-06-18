/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.Locale;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
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
	@Autowired
	private CategoryService categoryService;
		
	public static final String MANIFESTO_SLUG = "manifesto";
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayManifestoPage(Model model){
		Locale locale = LocaleContextHolder.getLocale();		
		
		model.addAttribute("article", articleService.getArticleBySlug(MANIFESTO_SLUG, locale.getLanguage()));
		//TODO obrisati dohvatanje cat
		model.addAttribute(categoryService.findAllSortedSubcategories("literature", Category.CATEGORY_ARTS));
		model.addAttribute("authorsForSignatures", authorDao.getAllAuthors());
		return "manifesto";
	}
	
}
