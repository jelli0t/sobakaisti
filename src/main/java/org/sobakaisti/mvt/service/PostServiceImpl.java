package org.sobakaisti.mvt.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.i18n.dao.I18nPostDao;
import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.i18n.service.I18nPostService;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javafx.geometry.Pos;

@Service
public abstract class PostServiceImpl<T extends Post, I extends I18nPost> 
		implements PostService<T>, I18nPostService<T, I> {	
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	/*
	 * Post DAO dependecies
	 * */
	private PostDao<T, I> postDao;
	
	@Autowired
	protected AuthorDao authorDao;
	@Autowired
	protected TagService tagService;
	@Autowired
	protected CategoryDao categoryDao;	
	@Autowired
	protected MediaService mediaService;		
	@Autowired
	private MessageSource messageSource;
		
	private Class<T> t;
		
	@SuppressWarnings("unchecked")
	public PostServiceImpl(PostDao<T, I> postDao) {
		super();
		this.t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.postDao = postDao;
	}
	
	
	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
	
	@Override
	public String getPostLanguage() {
		Locale locale = LocaleContextHolder.getLocale();		
		return locale != null ? locale.getLanguage() : StringUtil.DEFAULT_LANG_CODE;
	}
	
	@Override
	public T findById(int id) {	
		final String langCode = getPostLanguage();
		if(langCode.equals(StringUtil.DEFAULT_LANG_CODE)) 
			return postDao.find(id);
		else if(StringUtil.NON_DEFAULT_LANGS_LIST.contains(langCode))
			return postDao.getTranslatedPostById(id, langCode);
		else
			return null;
	}
	
	@Override
	public T findBySlug(String slug) {
		final String langCode = getPostLanguage();
		if(langCode.equals(StringUtil.DEFAULT_LANG_CODE)) 
			return postDao.findBySlug(slug);
		else if(StringUtil.NON_DEFAULT_LANGS_LIST.contains(langCode))
			return postDao.getTranslatedPostBySlug(slug, langCode);
		else
			return null;
	}
	
	@Override
	public List<T> findAll() {
		return postDao.findAllPostsByActiveStatus(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllPostOrderedByDate() {
		final PostFilter filter = new PostFilter();
		Pagination pagination = postDao.createPostPagination(new Pagination(), filter);		
		return (List<T>) postDao.findPostsSortedByDate(pagination, filter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllOrderedPostsByAuthor(Author author) {
		// Kreira filter po autoru 
		final PostFilter filter = new PostFilter(true, false, author);
		Pagination pagination = postDao.createPostPagination(new Pagination(), filter);		
		return (List<T>) postDao.findPostsSortedByDate(pagination, filter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllPostsByStatus(String status) {
		PostFilter filter = new PostFilter();
		filter.setActive(status.equals(ACTIVE_STATUS));
		Pagination pagination = postDao.createPostPagination(new Pagination(), filter);		
		return (List<T>) postDao.findPostsSortedByDate(pagination, filter);
	}
	
    @Override
    public I saveOrUpdateTranslatedPost(I i) {    	
    	return postDao.saveOrUpdate(i);
    }
    
    @Override
    public I findI18nPostByPostId(int postId, String lang, boolean fetchingPost) {
    	return postDao.findI18nPostByPostId(postId, lang, fetchingPost);
    }
	
	@Override
	public boolean delete(int id) {
		return postDao.delete(id);
	}
	
	@Override
	public abstract CommitResult commitDelete(int id);
	
	@Override
	public int countPostsByStatus(boolean isActive) {
		return postDao.countPostsByActiveStatus(isActive);
	}
	
	@Override
	public CommitResult switchPostStatus(int id) {
		CommitResult commited = null;
		int status = postDao.switchActiveStatus(id);
		if(status == ArticleService.ACTIVE) {
			logger.info("Post uspesno postavljen kao aktivan.");
			commited = new CommitResult(true, getMessage("post.status.changed.active"));
		}else if(status == ArticleService.INACTIVE) {
			logger.info("Post uspesno postavljen kao neaktivan.");
			commited = new CommitResult(true, getMessage("post.status.changed.nonactive"));
		} else {
			logger.info("Greska prilikom promene statusa posta!");
			commited = new CommitResult(false, getMessage("post.status.changed.failure"));
		}	
		return commited;
	}
	
	@Override
	public List<Author> findAllPostsAuthors() {
		return postDao.findAllPostsAuthors();
	}

	@Override
	public String addSuffixIfDuplicateExist(String slug) {
		int duplicates = postDao.countSlugDuplicates(slug);
		/* ako ima ponavljanja dodaj broj kao sufiks */
		if(duplicates > 0) {
			slug += ("-" + duplicates);
		}		
		return slug;
	}
	
	@Override
	public abstract T processAndSaveSubmittedPost(T post);
	
	

	@Override
	public List<Tag> fatchPostFullTagList(T t) {
		List<Tag> tagsReferences = null;
		List<Integer> tagsIdList = null;
		
		if(t instanceof Article)
			tagsReferences = ((Article) t).getTags();		
		else if(t instanceof Publication)
			tagsReferences = ((Publication) t).getTags();
		
		if(tagsReferences != null && !tagsReferences.isEmpty()) {
			tagsIdList = new ArrayList<Integer>(tagsReferences.size());
			for(Tag tag : tagsReferences)
				tagsIdList.add(tag.getId());
			
			return tagService.findListOfTagsByIdsList(tagsIdList);
		}
		return null;
	}
		
	
	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAllCategories();
	}
	
	@Override
	public Category getDefaultCategory() {
		return categoryDao.getDefaultCategory();
	}
	
	@Override
	public Model generateModelAttributesForPostsListing(Pagination pagination, String status, String authorSlug) {
		Author author = null;		
		boolean isActive = status != null && status.equals(ArticleService.ACTIVE_STATUS) ? true : false;
		/* ako je prosledjen autor */
		if(authorSlug != null)
			author = authorDao.findAuthorBySlug(authorSlug);
		/* kreira paginaciju */
		PostFilter filter = new PostFilter(isActive, false, author);
		
		return null;
	}
}
