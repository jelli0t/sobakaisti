package org.sobakaisti.mvt.service;

import java.util.Date;
import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.util.Pagination;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {

	public static final int LINE_HEIGHT = 16; 			// (px) visina jednog reda definisano CSS-om 
	public static final double FONT_WIDTH = 7.0;		// (px) sirina jednog slova
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
	public static final int ARTICLES_PER_LOAD = 5;
	public static final int INIT_ARTICLES_BUNDLE_SIZE = 9;
	public static final int RELATED_ARTICLES_BUNDLE_SIZE = 8;
	public static final int ARTICLES_PREV_ROW_SIZE = 4;
	
	public static final String ACTIVE_STATUS = "active";
	
	public static enum Manifesto {
		manifest, manifesto
	}
	
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth, String lang);
	
	public Article getArticleBySlug(String slug, String lang);
	
	/**
	 * Pronalazi entitet po prosledjenom ID-u
	 * */
	public Article findById(int id);
	
	/**
	 * Metoda cuva novi 
	 * */
	public Article saveArticle(Article article);
	
	/**
	 * @param limitResults
	 * */
	public List<Article> getArticlesOrderByDate(Pagination pagination);
	
	public List<Article> getArticlesOrderByDate(int index, int resultsLimit);
		
	public boolean deleteArticleById(int id);
	
	/**
	 * menja status clanka 1/0; publikovan/draft
	 * @return poruka o promeni statusa
	 * */
	public String switchArticleStatus(int articleId);
	
	/**
	 * Pronalazi sve clanke za datu kategoriju.
	 * */
	public List<Article> findAllArticlesForCategory(Category category, boolean isActive);
	
	/**
	 * Nalazi sve autore koji su pisali u datoj kategoriji
	 * */
	public List<Author> findAllArticlesAuthorsByCategory(Category category);

	 
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
	 * */
	Pagination createPostsPagination(Pagination pagination);
	
}
