/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;

/**
 * @author jelles
 *
 */
public interface ArticleDao extends PostDao<Article> {
	
	public static final String INTRO_ARTICLE_SLUG = "manifesto";
	
	public List<Article> getArticlesSortedByDate(Pagination pagination, PostFilter filter);
	
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

	/**
	 * Dohvata srodne clanke
	 * */
	public List<Article> findRelatedLatestArticles(Article exclude, int size);

	/**
	 * pronalazi prethodni i sledeci clanak
	 * */
	public List<Article> findNextAndPreviousArticle(Article article);
	
	
}