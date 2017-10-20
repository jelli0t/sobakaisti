/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.transaction.Transactional;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.mvt.service.TagService;
import org.sobakaisti.util.CalendarUtil;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.fabric.xmlrpc.base.Data;

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
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	
	private int charsPerRow;
	private int rowsPerPage;
	private int charsToFill;
	
	@Override
	public Article findById(int id) {		
		return articleDao.findArticleById(id);
	}
	
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
	public Article findArticleBySlug(String slug) {
		return articleDao.findArticleBySlug(slug.trim());
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
	public boolean createAndUploadArticle(int id, String title, String slug, Date postDate, String content, int authorId, int[] categoriesIds, int[] tagIds,
			MultipartFile file, int active) {
		Article article = new Article();
		article.setTitle(title);
		article.setSlug(addSuffixIfDuplicateExist(slug));
		article.setContent(content != null ? content : "");
		article.setPostDate(CalendarUtil.dateToCalendar(postDate));
		article.setActive(active);
		if(id != 0){
			article.setId(id);
		}
		/* ako je prosledjen ID autora pronadji ga i postavi ga kao autora izdanja */
		if(authorId > 0) {
			Author author = authorDao.getAuthorById(authorId);
			article.setAuthor(author);
		}
		/* ako ima odabranih Tagova dodaj ih na publication obj. */
		if(tagIds != null && tagIds.length > 0) {
			article.setTags(tagService.findListOfTagsByIdsArray(tagIds));
		}
		/* ako ima odabranih categorija nadji ih i dodeli clanku */
		if(categoriesIds != null && categoriesIds.length > 0) {
			article.setCategories(categoryService.findListOfCategoriesByIdsArray(categoriesIds));
		}
		
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			article.setFeaturedImage(fileName);
		}
		return articleDao.saveArticle(article);
	}


	private String addSuffixIfDuplicateExist(String slug) {
		int duplicates = articleDao.countSlugDuplicates(slug);
		/* ako ima ponavljanja dodaj broj kao sufiks */
		if(duplicates > 0) {
			slug += ("-" + duplicates);
		}		
		return slug;
	}
	
	@Override
	public List<Article> getArticlesOrderByDate(Pagination pagination, PostFilter filter) {
		return articleDao.getArticlesSortedByDate(pagination, filter);
	}

	@Override
	public Pagination createPostsPagination(Pagination pagination, PostFilter filter) {
		return articleDao.createPostPagination(pagination, filter);
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
		
	@Override
	public List<Article> findRelatedLatestArticles(Article exclude) {		
		return articleDao.findRelatedLatestArticles(exclude, RELATED_ARTICLES_BUNDLE_SIZE);
	}
	
	@Override
	public List<Article> findNextAndPreviousArticle(Article article) {
		return articleDao.findNextAndPreviousArticle(article);
	}
	
	@Override
	public List<Article> choosePrevAndNextArticle(Article article, List<Article> recommended) {
		List<Article> prevAndNext = new ArrayList<Article>(2);
		Article nextArticle = null;
		Article previousArticle = null;
		Calendar postDate = article.getPostDate();
		for(Article art : recommended) {			
			if(art.getAuthor().getId() == art.getAuthor().getId()) {
				if(art.getPostDate().after(postDate)) {
					nextArticle = art;	
				}else if(art.getPostDate().before(postDate)){
					previousArticle = art;
					break;
				}	
			}else {
				if(art.getPostDate().after(postDate)) {
					nextArticle = art;	
				}else if(art.getPostDate().before(postDate)){
					previousArticle = art;
					break;
				}	
			}						
		}
		prevAndNext.add(0, nextArticle);
		prevAndNext.add(1, previousArticle);
		return prevAndNext;
	}
	
	@Override
	public Map<String, Object> prepareModelAttributesForArticles(Pagination pagination, 
																String status, String authorSlug) {
		Map<String, Object> modelAttributes = new HashMap<String, Object>();
		boolean isActive = true;
		Author author = null;
		/* ako je prosledjen status */
		if(status != null)
			isActive = status.equals(ArticleService.ACTIVE_STATUS) ? true : false;
		/* ako je prosledjen autor */
		if(authorSlug != null)
			author = authorDao.findAuthorBySlug(authorSlug);
			
		PostFilter filter = new PostFilter(isActive, false, author);
		pagination = this.createPostsPagination(pagination, filter);
		/* count active / nonactive posts */
		final int active = this.countArticlesByStatus(true);
		final int nonActive = this.countArticlesByStatus(false);
		/* dohvata listu clanaka */
		final List<Article> articles = this.getArticlesOrderByDate(pagination, filter);
	
		modelAttributes.put("activeCount", active);
		modelAttributes.put("nonActiveCount", nonActive);
		modelAttributes.put("articles", articles);
		modelAttributes.put("pagination", pagination);
		modelAttributes.put("isActive", isActive);
		
		return modelAttributes;
	}

	@Override
	public Article getArticleFromPostRequest(PostRequest postRequest) {
		Article article = new Article();
		
		if(postRequest.getId() != 0) {
			article.setId(postRequest.getId());
		}
		/* naslov clanka */
		article.setTitle(postRequest.getTitle());
		/**
		 * pronalazi listu categorija na osnovu niza ID-eva
		 * */
		List<Category> categories = categoryService.findListOfCategoriesByIdsArray(postRequest.getCategoriesIds());
		article.setCategories(categories);
		
		
		
		
		return null;
	}
}
