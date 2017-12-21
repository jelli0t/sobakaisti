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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jelli0t
 *
 */
@SuppressWarnings("unchecked")
@Repository
public abstract class AbstractPostDao<T extends Post> implements PostDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractPostDao.class);
   
	@Autowired
	private SessionFactory sessionFactory;
    
	protected Class<T> entity;

	public AbstractPostDao() {        
        this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	@Transactional
	public T find(int id) {
		try {
			return (T) currentSession().get(entity, id);
		} catch (Exception e) {
			logger.warn("Greska pri pronalazenju enititeta sa id: "+id+". Uzrok: "+e.getMessage());
			return null;
		}
	}
	
	@Override
	@Transactional
	public T save(T t) {
		try {
			currentSession().saveOrUpdate(t);
			return t;
		} catch (Exception e) {
			logger.warn("Greska pri cuvanju enititeta. Uzrok: "+e.getMessage());
			return null;
		}	
	}
	
	@Override
	@Transactional
	public List<T> findAllPostsByActiveStatus(int status) {
		String HQL = "from "+entity.getName()+" t where t.postDate is not null" 
					+" and t.active = :status order by date(t.postDate) desc, t.id desc";	
		try {
			List<T> posts = currentSession().createQuery(HQL).setInteger("status", status).list();
			logger.info("Pronsao listu od "+posts.size()+" clanaka sa statusom: "+status);
			return posts;
		} catch (Exception e) {
			logger.warn("Greska pri dohvatanju liste enititeta. Vracam praznu listu. Uzrok: "+e.getMessage());
			return new ArrayList<T>(0);
		}
	}	
	
	@Override
	@Transactional
	public int countPostsByActiveStatus(boolean isActive) {
		int count = 0;
		int status = isActive ? Post.ACTIVE : Post.NONACTIVE;
		String HQL = "select count(t.id) from "+entity.getName()+" t where t.active = :status";
		try {			
			Long result = (Long) currentSession().createQuery(HQL).setInteger("status", status).uniqueResult();
			count = result.intValue();
		} catch (Exception e) {
			logger.warn("Greska pri brojanju Postova gde je staus = "+status+". Uzrok: "+e.getMessage());
		}
		return count;
	}	
	
	@Override
	@Transactional
	public int countSlugDuplicates(String slug) {
		int count = 0;
		String HQL = "select count(t.id) from "+entity.getName()+" t where t.slug like :slug";
		try {
			Long result = (Long) currentSession().createQuery(HQL).setString("slug", slug+"%").uniqueResult();
			count = result.intValue();
			logger.info("Pronsao sam da se slug: '"+slug+"' pojavljuje "+count+"x");
		} catch (Exception e) {
			logger.error("Greska pri pronalazenju duplikata slug-a: '"+slug+"'. Uzrok: "+e.getMessage());
		}
		return count;
	}
	
	@Override
	@Transactional
	public Pagination createPostPagination(Pagination pagination, PostFilter filter) {
		String HQL = "select count(t) from "+entity.getName()+" t where t.postDate is not null"
				+(filter.isActive() ? " and t.active = 1" : " and t.active = 0")
				+(filter.isNonactiveInlude() ? " or t.active = 0" : "") 
				+(filter.hasAuthor() ? " and t.author = :author" : "")
				+" order by date(t.postDate) desc, t.id desc";
		try {
			Query query = currentSession().createQuery(HQL);
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			Long count = (Long) query.uniqueResult();
			pagination.setMaxItems(count.intValue());
			logger.info("Ima ukupno "+count.longValue()+" postova");
		} catch (Exception e) {
			logger.error("Greska pri brojanju Postova. Uzrok: "+e.getMessage());
		}
		return pagination;
	}

	@Override
	@Transactional
	public List<T> findPostsSortedByDate(Pagination pagination, PostFilter filter) {
		List<T> posts;
		String HQL = "from "+entity.getName()+" t where t.postDate is not null" 
					 +(filter.isActive() ? " and t.active = 1" : " and t.active = 0")
					 +(filter.isNonactiveInlude() ? " or t.active = 0" : "")
					 +(filter.hasAuthor() ? " and t.author = :author" : "")
					 +" order by date(t.postDate) desc, t.id desc";
		try {
			Query query = currentSession().createQuery(HQL);
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
		String HQL = "select distinct t.author from "+entity.getName()+" t where t.active = 1";	
		try {
			List<Author> authors = currentSession().createQuery(HQL).list();
			return authors;
		} catch (Exception e) {
			logger.error("Greska pri trazenju autora koji su postovali. Uzrok: "+e.getMessage());
			return null;
		}	
	}	
	
	@Override
	@Transactional
	public int switchActiveStatus(int id) {
		try {
			T t = (T) currentSession().load(Publication.class, id);
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
				currentSession().update(entity.getName(), t);
				return t.getActive();
			}else {
				return -1;
			}
		}catch (Exception e) {
			return -1;
		}		
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			Session session = currentSession();
			T t = (T) session.get(entity.getClass(), id);
			if(t != null) {
				session.delete(t);
				return true;
			} else {
				session.close();
				return false;
			}			
		} catch (Exception e) {
			logger.error("Greska pri brisanju posta sa id: "+id+". Uzrok: "+e.getMessage());
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean delete(T t) {
		try {
			currentSession().delete(t.getClass().getName(), t);
			logger.info("Uspesno uklonjen post sa id: "+t.getId());
			return true;
		} catch (Exception e) {
			logger.error("Greska pri brisanju posta. Uzrok: "+e.getMessage());
			return false;
		}		
	}
}