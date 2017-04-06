package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Article;

public interface ArticleService {

	public static final int LINE_HEIGHT = 16; 			// (px) visina jednog reda definisano CSS-om 
	public static final double FONT_WIDTH = 7.0;		// (px) sirina jednog slova
	
	public static enum Manifesto {
		manifest, manifesto
	}
	
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth, String lang);
	
	public Article getArticleBySlug(String slug, String lang);
	
	/**
	 * Metoda cuva novi 
	 * */
	public Article saveArticle(Article article);
	
	public List<Article> getArticlesOrderByDate();
}
