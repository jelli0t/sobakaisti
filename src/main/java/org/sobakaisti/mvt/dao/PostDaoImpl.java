/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jelli0t
 * @param <T>
 *
 */
public class PostDaoImpl<T extends Post> implements PostDao<T> {
	
	protected Class<T> generic; 
	
	@Autowired
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public PostDaoImpl() {
		this.generic = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public T find(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (T) session.get(generic, id);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public T save(T t) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(generic.getName(), t);
			return t;
		} catch (Exception e) {
			System.err.println("Greska: "+e.getMessage());
			return null;
		}	
	}
	
	@Override
	@Transactional
	public List<T> findAllPostsByActiveStatus(int status) {
		String HQL = "from "+generic.getName()+" t where t.postDate is not null" 
					+" and t.active = :status order by date(t.postDate) desc, t.id desc";	
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<T> posts = session.createQuery(HQL).setInteger("status", status).list();
			System.out.println("Pronsao "+posts.size()+" clanaka sa statusom: "+status);
			return posts;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<T>(0);
		}		
	}	
	
	@Override
	@Transactional
	public int countPostsByActiveStatus(boolean isActive) {
		int count = 0;
		String HQL = "select count(t.id) from "+generic.getName()+" t where t.active = :status";
		try {
			Session session = sessionFactory.getCurrentSession();
			int status = isActive ? Post.ACTIVE : Post.NONACTIVE;
			Long result = (Long) session.createQuery(HQL).setInteger("status", status).uniqueResult();
			count = result.intValue();
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}
	
	
	@Override
	@Transactional
	public int countSlugDuplicates(String slug) {
		int count = 0;
		String HQL = "select count(t.id) from "+generic.getName()+" t where t.slug like :slug";
		try {
			Session session = sessionFactory.getCurrentSession();
			Long result = (Long) session.createQuery(HQL).setString("slug", slug+"%").uniqueResult();
			count = result.intValue();
			System.out.println("Pronasao sam "+count+" duplikata slug-ova na Artiklima");
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}
	
	@Override
	@Transactional
	public Pagination createPostPagination(Pagination pagination, PostFilter filter) {
		String HQL = "select count(t) from "+generic.getName()+" t where t.postDate is not null"
				+(filter.isActive() ? " and t.active = 1" : " and t.active = 0")
				+(filter.isNonactiveInlude() ? " or t.active = 0" : "") 
				+(filter.hasAuthor() ? " and t.author = :author" : "")
				+" order by date(t.postDate) desc, t.id desc";
	try {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(HQL);
		if(filter.hasAuthor())
			query.setParameter("author", filter.getAuthor());
		Long count = (Long) query.uniqueResult();
		pagination.setMaxItems(count.intValue());
		System.out.println("Ima ukupno "+count.longValue()+" postova");
	} catch (Exception e) {
		System.err.println("Greska pri brojanju postova! "+e.getMessage());
	}
		return pagination;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> findPostsSortedByDate(Pagination pagination, PostFilter filter) {
		List<T> posts;
		String HQL = "from "+generic.getName()+" t where t.postDate is not null" 
					 +(filter.isActive() ? " and t.active = 1" : " and t.active = 0")
					 +(filter.isNonactiveInlude() ? " or t.active = 0" : "")
					 +(filter.hasAuthor() ? " and t.author = :author" : "")
					 +" order by date(t.postDate) desc, t.id desc";
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(HQL);
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			posts = query.setFirstResult(pagination.getInitialItem())
							.setMaxResults(pagination.getItemsPerPage())
							.list();
		} catch (Exception e) {
			posts = new ArrayList<T>(0);
		}		
		return posts;
	}

	@Override
	public List<Author> findAllPostsAuthors() {
		String HQL = "select distinct t.author from "+generic.getName()+" t where t.active = 1";	
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Author> authors = session.createQuery(HQL).list();
			return authors;
		} catch (Exception e) {
			return null;
		}	
	}

	
	
	@Override
	@Transactional
	public int switchActiveStatus(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			T t = (T) session.load(Publication.class, id);
			if(t != null){
				int active = t.getActive();
				switch (active) {
				case 0:
					t.setActive(1);			
					break;
				case 1:
					t.setActive(0);
					break;
				default:
					return -1;
				}
				session.update(generic.getName(), t);
				return t.getActive();
			}else {
				return -1;
			}
		}catch (Exception e) {
			return -1;
		}		
	}

	@Override
	public boolean delete(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			T t = (T) session.get(generic.getClass(), id);
			if(t != null) {
				session.delete(t);
				return true;
			} else {
				session.close();
				return false;
			}			
		} catch (Exception e) {
			return false;
		}
	}
	
}
