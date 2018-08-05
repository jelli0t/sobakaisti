/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.i18n.dao.I18nPostDao;
import org.sobakaisti.mvt.i18n.model.I18nArticle;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.mvt.service.PostServiceImpl;
import org.sobakaisti.util.CalendarUtil;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author jelles
 *
 */
@Service
public class ArticleServiceImpl extends PostServiceImpl<Article, I18nArticle> implements ArticleService {
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	private ArticleDao articleDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private CategoryService categoryService;
		
	private int charsPerRow;
	private int rowsPerPage;
	private int charsToFill;
	
	@Autowired
	public ArticleServiceImpl(
			@Qualifier("articleDaoImpl") PostDao<Article, I18nArticle> postDao) {
		super(postDao);
		this.articleDao = (ArticleDao) postDao;
	}
	
	
	
	@Override
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth, String lang) {
		charsPerRow = (int) Math.floor(width / charWidth);
		rowsPerPage = (int) Math.floor(height / LINE_HEIGHT);
		
		String content = articleDao.getIntroBackgroundPost().getContent();
		int length = content.length();
		charsToFill = (int) (charsPerRow - Math.ceil(length % charsPerRow));
		
		logger.info("Params: Dimensions: "+width+"x"+height+"; Char width: "+charWidth+"; karaktera za popunjavanje: "+charsToFill
				+ " Chars x Rows: "+charsPerRow+"x"+rowsPerPage);
		
		List<String> row = new ArrayList<String>(rowsPerPage);
		
		if(TextUtil.notEmpty(content)){
			for(int i=0, j=0; i<rowsPerPage; i++){				
				if(j<length-charsPerRow) {
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
		return articleDao.findBySlug(slug);
	}
	
	@Override
	public Article getArticleBySlug(String slug, String lang) {
		Article article = (Article) articleDao.findBySlug(slug);
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
		if(articleDao.save(article) != null){			
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
		return articleDao.save(article) != null ? true : false;
	}
	
	@Override
	public List<Article> getArticlesOrderByDate(Pagination pagination, PostFilter filter) {
		return articleDao.findPostsSortedByDate(pagination, filter);
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

	@Override
	public Article processAndSaveSubmittedPost(Article post) {
		if(post != null) {		
			/* Ako je nova publikacija formiraj slug */
			if(post.getId() == 0)
				post.setSlug(addSuffixIfDuplicateExist(post.getSlug()));			
			/* postavlja autora */
			if(post.getAuthor() != null) {
				post.setAuthor(authorDao.getAuthorById(post.getAuthor().getId()));
			}
			/* set publications Tags */
			if(post.getTags() != null && post.getTags().size() > 0) {
				post.setTags(fatchPostFullTagList(post));
			}	
			
			/* set Article categories */
			if(post.getCategories() != null && post.getCategories().size() > 0) {
				post.setCategories(fatchPostFullCategoryList(post));
			} else {
				/* dodajemo listu sa podrazumevanom kategorijom */
				List<Category> categories = new ArrayList<Category>(1);
				categories.add(getDefaultCategory());
				post.setCategories(categories);
			}
			/* uploaded featured image */
			if(post.getFeaturedImage() != null) {
				Media featuredImageMedia = mediaService.findById(post.getFeaturedImage().getId());
				post.setFeaturedImage(featuredImageMedia);
			}
			/* set language*/
			post.setLang(getPostLanguage());
			logger.info("Cuvam: "+post);
			Article result = articleDao.saveOrUpdate(post);
			if (result != null) {
				result.setCommited(new Boolean(true));
				result.setCommitMessage(getMessage("publication.posted.successful"));
				return result;
			} else {
				post.setCommited(new Boolean(false));
				post.setCommitMessage(getMessage("publication.posted.failure"));
				return post;
			}
		}
		logger.warn("Nije prosledjen clanak za procesuiranje!");
		return null;
	}
	
	
	@Override
	public List<Category> fatchPostFullCategoryList(Article t) {
		List<Integer> categoriesIds = null;
		if(t != null && !t.getCategories().isEmpty()) {

			categoriesIds = new ArrayList<>(t.getCategories().size());
			for(Category category : t.getCategories()) {
				categoriesIds.add(category.getId());
			}
			logger.info("Iz Article objekta sam napravio listu ID-eva za odabrane "+categoriesIds.size()+" kategorije.");
		} else {
			logger.warn("Nije prosledjen Post ili je lista odabranih kategorija prazna!");
			categoriesIds = new ArrayList<>(0);
		}
		return categoryDao.findListOfCategoriesByIds(categoriesIds);
	}

	
	@Override
	public CommitResult commitDelete(int id) {
		boolean deleted = this.delete(id);
		String message = null;
		if(deleted)
			message = getMessage("article.delete.succesful");
		else 
			message = getMessage("article.delete.failure");
		return new CommitResult(deleted, message);
	}

	

}
