/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post.Origin;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.mvt.service.CommentService;
import org.sobakaisti.mvt.service.PostService;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showArtsRadialMenu(){
		return "mvt_artsMenu";
	}
	
	@RequestMapping(value="/{category}", method=RequestMethod.GET)
	public String showArtCategoryHome(Model model, @PathVariable String category){
		model = populateModelFromParameters(model, category, null, 0, PostService.INIT_POST_BUNDLE_SIZE);
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
	public String loadAdditionalArticlePreviewsFragment(Model model, @PathVariable String category, @RequestParam("loaded") int loaded) {		
		int remaind = 0;
		if(loaded != 0) 
			if(loaded < PostService.INIT_POST_BUNDLE_SIZE) 
				remaind = (int) (loaded % PostService.INIT_POST_BUNDLE_SIZE);	
		int size = remaind + PostService.LOADED_POST_BUNDLE_SIZE;
		model = populateModelFromParameters(model, category, null, loaded, size);
		return "commons/artArticlePreviews :: artsArticlePreviews";
	}
	
	
	@RequestMapping(value="/{category}/by/{author}/more", method=RequestMethod.GET)
	public String loadAdditionalArticlePreviewsFragment(Model model, @PathVariable String category, 
			@PathVariable String author, @RequestParam("loaded") int loaded) {		
		int remaind = 0;
		if(loaded != 0)
			if(loaded < PostService.INIT_POST_BUNDLE_SIZE) 
				remaind = (int) (loaded % PostService.INIT_POST_BUNDLE_SIZE);		
		int size = remaind + PostService.LOADED_POST_BUNDLE_SIZE;
		model = populateModelFromParameters(model, category, author, loaded, size);		
		model.addAttribute("art", category);
		model.addAttribute(TextUtil.URL_BASIS_ATTR_NAME, "arts" + TextUtil.SLASH_CHAR + category);
		return "commons/artArticlePreviews :: artsArticlePreviews";
	}
	
	@RequestMapping(value="/footer", method=RequestMethod.GET)
	public String loadingFooter() {
		return "commons/mvt_footer :: mvt_main_footer";
	}
	
	
	/* ARTICLES */
	@RequestMapping(value="/{category}/{article}", method=RequestMethod.GET)
	public String showArticleBySlug(@PathVariable("category") String category, 
			@PathVariable("article") String article, Model model) {
		if(article != null && !article.isEmpty()) {
			List<String> arts = new ArrayList<String>(1);
			arts.add(category);
			
			Article fullArticle = articleService.findBySlug(article);
			List<Article> relatedPosts = articleService.findRelatedLatestArticles(fullArticle);
//			model.addAttribute("sideArticles", articleService.findNextAndPreviousArticle(fullArticle));
//			model.addAttribute("sideArticles", articleService.choosePrevAndNextArticle(fullArticle, recommended));
			model.addAttribute("arts", arts);
			model.addAttribute(TextUtil.POST_ATTR_NAME, fullArticle);
			model.addAttribute(TextUtil.RELATED_POSTS_ATTR_NAME, relatedPosts);
//			model.addAttribute("authors", authors);
			model.addAttribute("author", fullArticle.getAuthor());
			model.addAttribute(PostService.POST_SORTING_ALLOWED_PARAM, false);
			model.addAttribute(PostService.META_CIRCLE_ALLOWED_ATTR, true);
			model.addAttribute(TextUtil.URL_BASIS_ATTR_NAME, "arts" + TextUtil.SLASH_CHAR + category);
			
			List<Comment> postComments = commentService.findPostComments(fullArticle.getId(), Origin.ARTICLE, 0, 3);
			if(postComments != null && postComments.size() > 0) {
				model.addAttribute("postComments", postComments);
				model.addAttribute(CommentService.COMMENTS_COUNT_MODEL_ATTRIBUTE_NAME, 
						commentService.countPostComments(fullArticle.getId(), Origin.ARTICLE));
			}
			model.addAttribute(CommentService.COMMENTS_AVAILABLE_ATTR_NAME, postComments != null && postComments.size() > 0);
			populateCommentsCountPerPostIndicator(model);
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
		List<String> arts = null;
		String urlBasis = "arts";
		
		PostFilter filter = new PostFilter(true, articleService.getPostLanguage(), authorSlug, category, startIndex, size);
		System.out.println(filter);
		
		if(category !=null && !category.isEmpty()) {
//			subcategories = categoryService.findAllSortedSubcategories(category, Category.CATEGORY_ARTS);
//			chosenArt = (subcategories != null && !subcategories.isEmpty()) ? subcategories.get(0) : null;
			/*
			 * Dohvata autore
			 * */
			authors = articleService.findAllPostsAuthorsInCategory(category);
			/*
			 * Dohvata slug-ove svih radova
			 * */
			arts = categoryService.findArtsSlugsSortedBySelectedArt(category);
			urlBasis += TextUtil.SLASH_CHAR + category;
			
			model.addAttribute("chosenArt", arts.get(0));
			model.addAttribute("authors", authors);		
			model.addAttribute("arts", arts);
			model.addAttribute(PostService.POST_SORTING_ALLOWED_PARAM, true);
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
		
		initArticles = articleService.find(filter);		
		/*
		 * ucitava mapu sa brojem kometar po postu
		 * */
		populateCommentsCountPerPostIndicator(model);
		
		/* postavi broj dohvacenh clanaka */		
//		if(startIndex == 0) {
//			initFetched = initArticles != null ? initArticles.size() : 0;
//			articlesFetched = initFetched;
//			System.out.println("inicijalni fetch: "+initFetched+" clanaka.");
//		}else {
//			articlesFetched += initArticles != null ? initArticles.size() : 0;
//			initFetched = 0;
//			System.out.println("ajax fetch: "+articlesFetched+" clanaka.");
//		}
		model.addAttribute("initArticles", initArticles);
		model.addAttribute(TextUtil.URL_BASIS_ATTR_NAME, urlBasis);
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
	
	private void populateCommentsCountPerPostIndicator(Model model) {
		Map<Integer, Integer> postToCommentsCountMap = commentService.getPostToCommentsCountMap(Origin.ARTICLE);
		if(postToCommentsCountMap != null)
			model.addAttribute(CommentService.COMMENTS_COUNT_PER_POST_ATTR_NAME, postToCommentsCountMap);
	}

}
