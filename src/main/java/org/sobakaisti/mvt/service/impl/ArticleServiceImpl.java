/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.transaction.Transactional;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private CategoryDao categoryDao;
	
	private int charsPerRow;
	private int rowsPerPage;
	private int charsToFill;
	
	@Override
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth, String lang) {
		charsPerRow = (int) Math.floor(width / charWidth);
		rowsPerPage = (int) Math.floor(height / LINE_HEIGHT);
		String content = articleDao.getintroArticleByLanguage(lang);
		int length = content.length();
		charsToFill = (int) (charsPerRow - Math.ceil(length % charsPerRow));
		
		System.out.println("Params: Dimensions: "+width+"x"+height+"; Char width: "+charWidth+"; karaktera za popunjavanje: "+charsToFill
				+ " Chars x Rows: "+charsPerRow+"x"+rowsPerPage);
		
		List<String> row = new ArrayList<String>(rowsPerPage);
		
		if(!content.equals("")){
			for(int i=0, j=0; i<rowsPerPage; i++){				
				if(j<length-charsPerRow){					
					row.add(content.substring(j, j+charsPerRow));
					j+=charsPerRow;
				}else if(j < length){
					String ending = content.substring(j)+" "+content.substring(0, charsToFill-1);
					row.add(ending);
					j = 0;
				}
			}
		}
		return row;
	}

	@Override
	public Article getArticleBySlug(String slug, String lang) {
		Article article = (Article) articleDao.getArticleBySlugTitle(slug, lang);
		return article;
	}

	@Override
	public Article saveArticle(Article article) {
		Locale defaultLocale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("messages", defaultLocale);
		
		Author author = authorDao.getAuthorById(article.getAuthor().getId());
		article.setCategories(prepareCategoriesForArticle(article));		
		article.setAuthor(author);
		article.setPostDate(Calendar.getInstance());		
		boolean success = articleDao.saveArticle(article);
		
		if(success){			
			return article;
		} else {
			return null;
		}
	}

	@Override
	public List<Article> getArticlesOrderByDate(int resultsLimit) {
		return articleDao.getArticlesSortedByDate(resultsLimit);
	}
	@Override
	public List<Article> getArticlesOrderByDate(int index, int resultsLimit) {
		return articleDao.getArticlesSortedByDate(index, resultsLimit);
	}
	
	/**
	 * metoda priprema (dohvata) kategorije za prosledjeni clanak
	 * */
	private List<Category> prepareCategoriesForArticle(Article article){
		List<Category> categories = null;
		if(article.getCategories() != null){
			final int size = article.getCategories().size();
			categories = new ArrayList<Category>(size);
			List<Integer> ids = new ArrayList<Integer>(size);			
			
			for(Category c : article.getCategories()){
				ids.add(c.getId());				
			}
			System.out.println("dodao ids u listu, size: "+ids.size());	
			categories = categoryDao.getAllCategoriesByIds(ids);	
		}
		return categories;
	}

	@Override
	public boolean deleteArticleById(int id) {
		return articleDao.deleteArticleById(id);
	}

	@Override
	public String switchArticleStatus(int articleId) {
		int articleStatus = articleDao.switchArticleStatus(articleId);		
		if(articleStatus == ACTIVE){
			return "Uspesno ste publikovali clanak.";
		}else if(articleStatus == INACTIVE) {
			return "Uspesno ste deaktivirali clanak.";
		}
		return null;
	}
	
	@Override
	public int countArticlesByStatus(boolean isActive) {
		return articleDao.countArticlesByStatus(isActive);
	}

	@Override
	public List<Article> findAllArticlesByStatus(String status) {
		List<Article> articles;
		if(status.equals(ACTIVE_STATUS)) {
			articles = articleDao.findAllArticlesByStatus(1);
		}else {
			articles = articleDao.findAllArticlesByStatus(0);
		}
		return articles;
	}
	
	@Override
	public List<Author> findAllArticlesAuthorsByCategory(Category category) {
		return articleDao.findAllArticlesAuthorsByCategory(category);
	}
	
	@Override
	public List<Article> findAllArticlesForCategory(Category category, boolean isActive) {
		// TODO Auto-generated method stub
		return articleDao.findAllArticlesForCategory(category, isActive);
	}
	
	@Override
	public List<Article> findAriclesBundleByCategory(Category category, int from, int size, boolean isActive){
		return articleDao.findAriclesBundleByCategory(category, from, size, isActive);
	}
	
	@Override
	public List<Article> findArticlesBundleForCategoryByAuthor(Category category, Author author, int from, int size, boolean isActive) {
		return articleDao.findArticlesBundleForCategoryByAuthor(category, author, from, size, isActive);
	}
	
}
