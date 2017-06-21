/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/arts")
public class ArtsController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private ArticleService articleService;
	
	private int index = 0;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showArtsRadialMenu(){
		return "mvt_artsMenu";
	}
	
	@RequestMapping(value="/{category}", method=RequestMethod.GET)
	public String showLiteratureHome(Model model, @PathVariable String category){
		List<Category> subcategories = categoryService.findAllSortedSubcategories(category, Category.CATEGORY_ARTS);
		Category chosenArt = subcategories != null ? subcategories.get(0) : null;
		model.addAttribute("chosenArt", chosenArt);
		//TODO napista sve autore koji su relevantni za odabranu kat.
		model.addAttribute("authors", authorDao.getAllAuthors());		
		model.addAttribute("arts", subcategories);
		
		/* first articles to display */
		index = 0;
		List<Article> initialArticles = articleService.getArticlesOrderByDate(0, 11);
		if(initialArticles != null && initialArticles.size() > 0){
			model.addAttribute("initArticles", initialArticles);
			index = initialArticles.size();
			System.out.println("Idnex postavljam na: "+index);
		}		
		return "art";
	}
	
	@RequestMapping(value="/{category}/{author}", method=RequestMethod.GET)
	public String showArtsHomeFilteredByAuthor(Model model, 
			@PathVariable String category, @PathVariable String author) {
		Author chosenAuthor = authorDao.findAuthorBySlug(author);
		if(category != null) {
			List<Category> subcategories = categoryService.findAllSortedSubcategories(category, Category.CATEGORY_ARTS);
			Category chosenArt = subcategories != null ? subcategories.get(0) : null;
			model.addAttribute("chosenArt", chosenArt);
			model.addAttribute("arts", subcategories);
		}		
		if(chosenAuthor != null) {
			model.addAttribute("chosenAuthor", chosenAuthor);
		}	
		model.addAttribute("authors", authorDao.getAllAuthors());
		return "art";
	}
	
	
	@RequestMapping(value="/load_more_articles", method=RequestMethod.GET)
	public String loadAdditionalArticlePreviewsFragment(Model model){		
		List<Article> additionalArticles = articleService.getArticlesOrderByDate(index, 5);
		if(additionalArticles.size() > 0){
			model.addAttribute("additionalArticles", additionalArticles);
			index += ArticleService.ARTICLES_PER_LOAD;
		}
		return "commons/artArticlePreviews :: artsArticlePreviews";
	}

}
