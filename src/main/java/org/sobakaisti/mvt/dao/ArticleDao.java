/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
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
	 * @return boolean
	 * */
	public boolean saveArticle(Article article);
	
	
	public List<Article> getArticlesSortedByDate(int resultsLimit);
	
	/**
	 * dohvata sve clanke sortirane po datumu uz paginacju i limit
	 * */
	public List<Article> getArticlesSortedByDate(int index, int resultsLimit);
	
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
	 * Pronalazi sve clanke za datu kategoriju.
	 * */
	public List<Article> findAllArticlesForCategory(Category category, boolean isActive);

	List<Author> findAllArticlesAuthorsByCategory(Category category);

	/**
	 * Dohvata grupu clanaka na osnovu prosledjene kategorije od zadatog ideksa i broja max rezultata
	 * @param category
	 * @param from	pocetni index
	 * @param size	velicina niza
	 * @param isActive 	samo aktivne
	 * */
	public List<Article> findAriclesBundleByCategory(Category category, int from, int size, boolean isActive);
		
	/**
	 * Dohvata grupu clanaka na osnovu prosledjene kategorije za Autora, od zadatog ideksa i broja max rezultata
	 * @param category
	 * @param author	autor clanaka
	 * @param from	pocetni index
	 * @param size	velicina niza
	 * @param isActive 	samo aktivne
	 * */
	public List<Article> findArticlesBundleForCategoryByAuthor(Category category, Author author, int from, int size, boolean isActive);

	/**
	 * TODO podici na veci nivo hijerarhije pa naslediti
	 * Dohvata sve clanke na osnovu prosledjenog statusa
	 * @param status
	 * */
	List<Article> findAllArticlesByStatus(int status);

	/**
	 * Broji clanke na osnovu statusa
	 * */
	int countArticlesByStatus(boolean isActive);

	/**
	 * TODO podici u nadklasu 
	 * proverava da li ima duplikata za trazeni slug
	 * @param slug
	 * */
	public int countSlugDuplicates(String slug);

	Article findArticleBySlug(String slug);
	
	/**
	 * Dohvata srodne clanke
	 * */
	public List<Article> findRelatedLatestArticles(Article exclude, int size);
}