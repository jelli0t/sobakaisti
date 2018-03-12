/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.util.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private ArticleService articleService;
	
	private int initFetched = 0;
	private int articlesFetched = 0;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showArtsRadialMenu(){
		return "mvt_artsMenu";
	}
	
	@RequestMapping(value="/{category}", method=RequestMethod.GET)
	public String showArtCategoryHome(Model model, @PathVariable String category){
		model = populateModelFromParameters(model, category, "", 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE);
		return "art";
	}
	
	@RequestMapping(value="/{category}/by/{author}", method=RequestMethod.GET)
	public String showArtsHomeFilteredByAuthor(Model model, 
			@PathVariable String category, @PathVariable String author) {
		model = populateModelFromParameters(model, category, author, 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE);
		return "art";
	}
	
	@RequestMapping(value="/popular/{category}", method=RequestMethod.GET)
	public String showPopularArticlesPreview(Model model, @PathVariable String category) {
		model = populateModelFromParameters(model, category, "", 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE);
		model.addAttribute("popular", true);
		return "art";
	}
	
	@RequestMapping(value="/popular/{category}/by/{author}", method=RequestMethod.GET)
	public String showPopularArticlesPreview(Model model, 
			@PathVariable String category, @PathVariable String author) {
		model = populateModelFromParameters(model, category, author, 0, ArticleService.INIT_ARTICLES_BUNDLE_SIZE);
		model.addAttribute("popular", true);
		return "art";
	}
	
	@RequestMapping(value="/{category}/more", method=RequestMethod.GET)
	public String loadAdditionalArticlePreviewsFragment(Model model, @PathVariable String category){
		int fetched = 0;
		boolean isInit = false;
		if(initFetched > 0) {
			fetched = initFetched;
			isInit = true;	
		}else {
			fetched = articlesFetched;
		}
		int size = calculateNumberOfArticlesToFetch(fetched, ArticleService.ARTICLES_PREV_ROW_SIZE, isInit);
		model = populateModelFromParameters(model, category, "", fetched, size);		
		model.addAttribute("art", category);
		return "commons/artArticlePreviews :: artsArticlePreviews";
	}
	
	@RequestMapping(value="/{category}/by/{author}/more", method=RequestMethod.GET)
	public String loadAdditionalArticlePreviewsFragment(Model model, @PathVariable String category,
			@PathVariable String author){
		int fetched = 0;
		boolean isInit = false;
		/* ako je init ucitavanje clanaka */
		if(initFetched > 0) {
			fetched = initFetched;
			isInit = true;	
		}else {
			fetched = articlesFetched;
		}
		int size = calculateNumberOfArticlesToFetch(fetched, ArticleService.ARTICLES_PREV_ROW_SIZE, isInit);
		model = populateModelFromParameters(model, category, author, fetched, size);		
		model.addAttribute("art", category);
		return "commons/artArticlePreviews :: artsArticlePreviews";
	}
	
	@RequestMapping(value="/footer", method=RequestMethod.GET)
	public String loadingFooter() {
		return "commons/mvt_footer :: mvt_main_footer";
	}
	
	
	/* ARTICLES */
	@RequestMapping(value="/{category}/{article}", method=RequestMethod.GET)
	public String showArticleBySlug(@PathVariable("category") String category,  @PathVariable("article") String article, Model model) {
		if(article != null && !article.isEmpty()) {
			List<Category> arts = new ArrayList<Category>(1);
			arts.add(categoryService.findCategoryBySlug(category));
			System.out.println("Cat: "+category+"; Art_slug: "+article);
			Article fullArticle = articleService.findArticleBySlug(article);
			List<Article> recommended = articleService.findRelatedLatestArticles(fullArticle);
//			model.addAttribute("sideArticles", articleService.findNextAndPreviousArticle(fullArticle));
			model.addAttribute("sideArticles", articleService.choosePrevAndNextArticle(fullArticle, recommended));
			model.addAttribute("arts", arts);
			model.addAttribute("article", fullArticle);
			model.addAttribute("initArticles", recommended);
		}
		return "article";
	}
	
	/**
	 * pomocna metoda koja popunjava Model pre renderovanja strane
	 * */
	private Model populateModelFromParameters(Model model, String category, String authorSlug, int startIndex, int size) {
		List<Category> subcategories = null;
		Category chosenArt = null;
		List<Author> authors  = null;
		Author chosenAuthor = null;
		List<Article> initArticles = null;
		
		PostFilter filter = new PostFilter(false, articleService.getPostLanguage(), authorSlug, category, startIndex, size);
		
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
		System.out.println("Dohvatam clanke za kategoriju: "+chosenArt.getName()+"; od indeksa: "+startIndex+" size: "+size);
		/* ako je odabran autor dohvati samo njegove clanke */
		if(chosenAuthor != null) {
			initArticles = articleService.findArticlesBundleForCategoryByAuthor(chosenArt, chosenAuthor, startIndex, size, true);
		}else {
			initArticles = articleService.findAriclesBundleByCategory(chosenArt, startIndex, size, true);
		}
		/* postavi broj dohvacenh clanaka */		
		if(startIndex == 0) {
			initFetched = initArticles != null ? initArticles.size() : 0;
			articlesFetched = initFetched;
			System.out.println("inicijalni fetch: "+initFetched+" clanaka.");
		}else {
			articlesFetched += initArticles != null ? initArticles.size() : 0;
			initFetched = 0;
			System.out.println("ajax fetch: "+articlesFetched+" clanaka.");
		}
		
		model.addAttribute("initArticles", initArticles);
		return model;
	}
	
	private int calculateNumberOfArticlesToFetch(int fetched, int rowSize, boolean isInit) {			
		fetched = isInit ? --fetched : fetched;
		int lack = rowSize - (int) (fetched % rowSize);	
		lack = (lack==rowSize) ? 0 : lack;
		
		System.out.println("Kalkulisem kolko treba jos clanaka da ucitam:");
		System.out.println("dohvaceno: "+fetched);
		System.out.println("nedostaje za pun red: "+lack);
		System.out.println("treba da dohvatim: "+(rowSize + lack));
		return rowSize + lack;
	}

}
