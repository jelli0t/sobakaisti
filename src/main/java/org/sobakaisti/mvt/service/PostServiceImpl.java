package org.sobakaisti.mvt.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFactory;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl<T extends Post> implements PostService<T> {
	
	@Autowired
	protected PostDao<T> postDao;	
	@Autowired
	protected AuthorDao authorDao;
	
	private Class<T> t;
	
	@SuppressWarnings("unchecked")
	public PostServiceImpl() {
		super();
		this.t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

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
			PostFactory factory = PostFactory.getFactory(t);		
			@SuppressWarnings("unchecked")
			T post = (T) factory.processPostRequest(postRequest);
			return postDao.save(post);
		} catch (Exception e) {
			System.err.println("Neuspelo procesiranje postRequesta: "+e.getMessage());
			return null;
		}		
	}

}
