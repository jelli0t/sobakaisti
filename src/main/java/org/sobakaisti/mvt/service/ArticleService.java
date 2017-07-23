package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Tag;

public interface ArticleService {

	public static final int LINE_HEIGHT = 16; 			// (px) visina jednog reda definisano CSS-om 
	public static final double FONT_WIDTH = 7.0;		// (px) sirina jednog slova
	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;
	public static final int ARTICLES_PER_LOAD = 5;
	
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
	 * @param limitResults
	 * */
	public List<Article> getArticlesOrderByDate(int resultsLimit);
	
	public List<Article> getArticlesOrderByDate(int index, int resultsLimit);
		
	public boolean deleteArticleById(int id);
	
	/**
	 * menja status clanka 1/0; publikovan/draft
	 * @return poruka o promeni statusa
	 * */
	public String switchArticleStatus(int articleId);
	
}
