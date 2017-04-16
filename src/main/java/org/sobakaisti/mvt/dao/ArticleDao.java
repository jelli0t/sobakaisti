/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Tag;

/**
 * @author jelles
 *
 */
public interface ArticleDao {
	
	/**
	 * Metoda pronalazi i vraca clanak po Naslovu na zadatom jeziku
	 * @param title		Naslov clanka
	 * @param lang		Kod jezika na kom je clanak
	 * */
	public Article getArticleBySlugTitle(String slug, String lang);

	public String getArticleById(int id);
	
	/**
	 * Nalazi clanak po ID i vraca ga
	 * */
	public Article findArticleById(int id);
	
	/**
	 * Metoda vraca clanak na onovu prosledjenog lang koda.
	 * @param langCode	Kod jezika (rs, en, it, fr...)
	 * */
	public String getintroArticleByLanguage(String langCode);
	
	/**
	 * Metoda cuva clanak
	 * @param clanka
	 * @return sacuvani clanak
	 * */
	public Article saveArticle(Article article);
	
	
	public List<Article> getArticlesSortedByDate();
	
	/**
	 * Brise clanak na osnovu prosledjenog ID
	 * @param id
	 * */
	public boolean deleteArticleById(int id);
	
	/**
	 *  metoda menja status clanka active 1/0
	 *  public / draft
	 * */
	public int switchArticleStatus(int articleId);
	
	/**
	 * metoda koja pronalazi sve tagove na osnovu prosledjene fraze
	 * */
	public List<Tag> searchTagsByPhrase(String phrase);
}