package org.sobakaisti.mvt.service;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public abstract class PostServiceImpl<T extends Post> implements PostService<T> {	
	
	private PostDao<T> postDao;
	
	@Autowired
	protected AuthorDao authorDao;
	@Autowired
	protected TagService tagService;
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	protected MediaService mediaService;	
	/*
	 * Post factory instances
	 * */
	@Autowired
	protected PostFactory articlePostFactory;
	
	@Autowired
	protected MessageSource messageSource;
		
	private Class<T> t;
	protected Map<String, PostFactory> postFactoriesMap;
	
	@SuppressWarnings("unchecked")
	public PostServiceImpl(PostDao<T> postDao) {
		super();
		this.t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.postDao = postDao;
	}
	
	@PostConstruct
	private void init() {
		postFactoriesMap = new HashMap<String, PostFactory>(3);
		postFactoriesMap.put(ARTICLE_CLASS_NAME, articlePostFactory);
	}
	
	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, new Locale("rs"));
	}
	
	@Override
	public T findById(int id) {
		return postDao.find(id);
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
	public boolean delete(int id) {
		return postDao.delete(id);
	}

	@Override
	public int countPostsByStatus(boolean isActive) {
		return postDao.countPostsByActiveStatus(isActive);
	}
	
	
	//TODO umesto hardcodovanih poruka dohvati ih is resursa!
	@Override
	public String switchPostStatus(int id) {
		int status = postDao.switchActiveStatus(id);
		if(status == ArticleService.ACTIVE){
			return "Uspesno ste publikovali Izdanje.";
		}else if(status == ArticleService.INACTIVE) {
			return "Uspesno ste deaktivirali izdanje.";
		}
		return null;
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
	public T processAndSavePostRequest(PostRequest postRequest) {
		try { 
			PostFactory factory = postFactoriesMap.get(t.getName());
			@SuppressWarnings("unchecked")
			T post = (T) factory.processPostRequest(postRequest);
			return postDao.save(post);
		} catch (Exception e) {
			System.err.println("Neuspelo procesiranje postRequesta: "+e.getMessage());
			return null;
		}		
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
	
}
