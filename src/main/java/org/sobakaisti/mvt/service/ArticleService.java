package org.sobakaisti.mvt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.i18n.model.I18nArticle;
import org.sobakaisti.mvt.i18n.service.I18nPostService;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService extends PostService<Article>, I18nPostService<Article, I18nArticle> {

	public static final String INTRO_BACKGROUND_POST_SLUG = "intro-background";
	public static final int LINE_HEIGHT = 16; 			// (px) visina jednog reda definisano CSS-om 
	public static final double FONT_WIDTH = 7.0;		// (px) sirina jednog slova
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
	public static final int ARTICLES_PER_LOAD = 5;
	public static final int INIT_ARTICLES_BUNDLE_SIZE = 9;
	public static final int RELATED_ARTICLES_BUNDLE_SIZE = 8;
	public static final int ARTICLES_PREV_ROW_SIZE = 4;
	
	
	
	public static enum Manifesto {
		manifest, manifesto
	}
	
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth, String lang);
	
	public Article getArticleBySlug(String slug, String lang);
	
	
	
	/**
	 * Metoda cuva novi 
	 * */
	public Article saveArticle(Article article);
	
	/**
	 * pronalazi listu clanaka na osnovu prosledjenog filtera 
	 * i sortirano po datumu objave opadajucim redosledom
	 * @param pagination
	 * @param filter
	 * */
	public List<Article> getArticlesOrderByDate(Pagination pagination, PostFilter filter);
	
	
	/**
	 * Pronalazi sve clanke za datu kategoriju.
	 * */
	public List<Article> findAllArticlesForCategory(Category category, boolean isActive);
		
	 
	public List<Article> findAriclesBundleByCategory(Category category, int from, int size, boolean isActive);

	public List<Article> findArticlesBundleForCategoryByAuthor(Category category, Author author, int from, int size,
			boolean isActive);

	/**
	 * TODO podici na veci nivo hijerarhije pa naslediti
	 * Pronalazi sve clanke na osnovu izabranog statusa
	 * @param status 
	 * */
	public List<Article> findAllArticlesByStatus(String status);

	/**
	 * TODO podici na visi nivo hijer.
	 * Broji sve clanke na osnovu statusa
	 * @param isActive
	 * */
	public int countArticlesByStatus(boolean isActive);

	/**
	 * TODO sa publication upload metodom razmotri sjedinjavanje
	 * upload articles
	 * */
	boolean createAndUploadArticle(int id, String title, String slug, Date postDate, String content, int authorId, 
			int[] categoriesIds, int[] tagIds, MultipartFile file, int active);

	Article findArticleBySlug(String slug);
	
	/**
	 * Pronalazi srodne clanke
	 * */
	public List<Article> findRelatedLatestArticles(Article exclude);

	public List<Article> findNextAndPreviousArticle(Article article);
	
	/**
	 * Izdvaja predlog za prethodni i sledeci clanak na sigle_post pageu
	 * */
	public List<Article> choosePrevAndNextArticle(Article article, List<Article> recommended);

	/**
	 * Paginacija postova
	 * @param pagination
	 * @param filter
	 * */
	Pagination createPostsPagination(Pagination pagination, PostFilter filter);
	
	/**
	 * Priprema listu atributa za model objekat, pomocu koje renderuje stranu
	 * @param pagination
	 * @param status
	 * @param authorSlug
	 * */
	Map<String, Object> prepareModelAttributesForArticles(Pagination pagination, String status, String authorSlug);
	
	/**
	 * konvertuje PostRequest sa forme u konkreta article objekat
	 * */
	public Article getArticleFromPostRequest(PostRequest postRequest);
	
	/**
	 * Dohvata potpunu listu selektovanih kategorija ia article objekta
	 * */
	public List<Category> fatchPostFullCategoryList(Article t);
}
