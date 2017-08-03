/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.ArrayList;
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
		model = populateModelFromParameters(model, category, "");
		return "art";
	}
	
	@RequestMapping(value="/{category}/by/{author}", method=RequestMethod.GET)
	public String showArtsHomeFilteredByAuthor(Model model, 
			@PathVariable String category, @PathVariable String author) {
		model = populateModelFromParameters(model, category, author);
		return "art";
	}
	
	@RequestMapping(value="/popular/{art}/by/{author}", method=RequestMethod.GET)
	public String showPopularArticlesPreview(Model model, 
			@PathVariable String category, @PathVariable String author) {
		model = populateModelFromParameters(model, category, author);
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
	
	/**
	 * pomocna metoda koja popunjava Model pre renderovanja strane
	 * */
	private Model populateModelFromParameters(Model model, String category, String authorSlug) {
		List<Category> subcategories = null;
		Category chosenArt = null;
		List<Author> authors  = null;
		Author chosenAuthor = null;
		List<Article> initArticles = null;
		
		if(category !=null && !category.isEmpty()) {
			subcategories = categoryService.findAllSortedSubcategories(category, Category.CATEGORY_ARTS);
			chosenArt = (subcategories != null && !subcategories.isEmpty()) ? subcategories.get(0) : null;
			authors = articleService.findAllArticlesAuthorsByCategory(chosenArt);
			
			model.addAttribute("chosenArt", chosenArt);
			model.addAttribute("authors", authors);		
			model.addAttribute("arts", subcategories);
		}
		/* ako je prosledjeni autor slug nije prazan nadji autora */
		if(authorSlug != null && !authorSlug.isEmpty()) {
			for(Author a : authors) {
				if(a.getSlug().equals(authorSlug)) {
					chosenAuthor = a;
					break;
				}
			}
			model.addAttribute("chosenAuthor", chosenAuthor);
		}
		/* ako je odabran autor dohvati samo njegove clanke */
		if(chosenAuthor != null) {
			initArticles = articleService.findArticlesBundleForCategoryByAuthor(chosenArt, chosenAuthor, 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE, true);
		}else {
			initArticles = articleService.findAriclesBundleByCategory(chosenArt, 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE, true);
		}
		model.addAttribute("initArticles", initArticles);
		return model;
	}

}
