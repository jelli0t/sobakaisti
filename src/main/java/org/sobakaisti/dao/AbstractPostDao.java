/**
 * 
 */
package org.sobakaisti.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.i18n.dao.I18nPostResultTransformer;
import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.IntroPost;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.enums.PostStatus;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.StringUtil;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractPostDao<T extends Post, I extends I18nPost> 
	implements PostDao<T, I> {
	protected static final Logger logger = LoggerFactory.getLogger(AbstractPostDao.class);
   
	@Autowired
	private SessionFactory sessionFactory;
    
	protected Class<T> entity;
	protected Class<I> i18nPost;

	public AbstractPostDao() {        
        this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.i18nPost = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	protected String getPostLanguage() {
		Locale locale = LocaleContextHolder.getLocale();		
		return locale != null ? locale.getLanguage() : StringUtil.DEFAULT_LANG_CODE;
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
	public T findBySlug(String slug) {
		String HQL = "from "+entity.getName()+" t where t.slug = :slug";
		try {
			return (T) currentSession().createQuery(HQL)
										.setString("slug", slug)
										.uniqueResult();
		} catch (Exception e) {
			logger.warn("Greska pri pronalazenju enititeta sa slugom: "+slug+". Uzrok: "+e.getMessage());
			return null;
		}
	}
	
	@Override
	@Transactional
	public List<T> find(PostFilter postFilter) {
		String HQL = "select p from "+entity.getName()+" p"
				+ (TextUtil.notEmpty(postFilter.getCategorySlug()) ? 
						" left join p.categories cat where cat.slug = :categorySlug" : " where")				
				+ (TextUtil.notEmpty(postFilter.getAuthorSlug()) ? " and p.author.slug = :authorSlug" : "")				
				+ (postFilter.isActive() ? " and p.active = 1" : "")
				+ " and p.postDate is not null order by date(p.postDate) desc, p.id desc";
	
		Query query = currentSession().createQuery(HQL);
		if(TextUtil.notEmpty(postFilter.getAuthorSlug()))
			query.setString("authorSlug", postFilter.getAuthorSlug());
		if(TextUtil.notEmpty(postFilter.getCategorySlug()))
			query.setString("categorySlug", postFilter.getCategorySlug());
		query.setFirstResult(postFilter.getFrom());
		query.setMaxResults(postFilter.getSize());		
		
		return query.list();
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
	public T saveOrUpdate(T t) {
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
	public I saveOrUpdate(I i) {
		try {
			currentSession().saveOrUpdate(i);
			return i;
		} catch (Exception e) {
			logger.warn("Greska pri cuvanju prevoda posta. Uzrok: "+e.getMessage());
			return null;
		}	
	}
	
	@Override
	@Transactional
	public I findI18nPostByPostId(int postId, String lang, boolean fetchingPost) {
		final String post = entity.getSimpleName().toLowerCase();	
		String HQL = "from "+i18nPost.getName()+" ip"
				+ (fetchingPost ? " join fetch ip."+post+"" : "")
				+ " where ip."+post+".id = :post_id and ip.lang = :lang";
		try {
			Query query = currentSession().createQuery(HQL);
			query.setInteger("post_id", postId);
			query.setString("lang", lang);
			return (I) query.uniqueResult();
		}catch (Exception e) {
			logger.warn("Greska pri trazenju prevoda posta sa ID: "+postId+". Uzrok: "+e.getMessage());
			return null;
		}		
	}
	
	@Override
	@Transactional
	public List<T> findAllPostsByActiveStatus(int status) {
		String HQL = "from "+entity.getName()+" t where t.postDate is not null" 
					+" and t.active = :status"
					+ (StringUtil.notEmpty(getPostLanguage()) ? " and lang = :lang" : "")
					+ " order by date(t.postDate) desc, t.id desc";	
		try {
			Query query = currentSession().createQuery(HQL);
			query.setInteger("status", status);
			if(StringUtil.notEmpty(getPostLanguage())) 
					query.setString("lang", getPostLanguage());
			List<T> posts = query.list();
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

		int status = PostStatus.byValue(isActive).getValue();
		var HQL = "select count(t.id) from "+entity.getName()+" t where t.active = :status";

		try {
			Long result = (Long) currentSession()
					.createQuery(HQL)
					.setInteger("status", status)
					.uniqueResult();

			return result.intValue();
		} catch (Exception e) {
			logger.warn("Greska pri brojanju Postova gde je staus = "+status+". Uzrok: "+e.getMessage());
			return 0;
		}
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
				+ (StringUtil.notEmpty(getPostLanguage()) ? " and lang = :lang" : "")
				+" order by date(t.postDate) desc, t.id desc";
		try {
			Query query = currentSession().createQuery(HQL);
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			/* dodaj jezik u query */
			if(StringUtil.notEmpty(getPostLanguage())) 
				query.setString("lang", getPostLanguage());
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
					 + (filter.isActive() ? " and t.active = 1" : " and t.active = 0")
					 + (filter.isNonactiveInlude() ? " or t.active = 0" : "")
					 + (filter.hasAuthor() ? " and t.author = :author" : "")
					 + (StringUtil.notEmpty(getPostLanguage()) ? " and lang = :lang" : "")
					 + " order by date(t.postDate) desc, t.id desc";
		try {
			Query query = currentSession().createQuery(HQL);
			/* dodaj autora u query */
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			/* dodaj jezik u query */
			if(StringUtil.notEmpty(getPostLanguage())) 
				query.setString("lang", getPostLanguage());
			posts = query.setFirstResult(pagination.getInitialItem())
							.setMaxResults(pagination.getItemsPerPage())
							.list();
		} catch (Exception e) {
			posts = new ArrayList<T>(0);
		}		
		return posts;
	}

	@Override
	@Transactional
	public List<Author> findAllPostsAuthors() {
		String HQL = "select distinct p.author from "+entity.getName()+" p where p.active = 1";	
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
	public List<Author> findAllPostsAuthorsInCategory(String categorySlug) {
		String HQL = "select distinct p.author from "+entity.getName()+" p left join p.categories cat"
				+ " where cat.slug = :categorySlug and p.active = 1";
		try {
			List<Author> authors = currentSession().createQuery(HQL).setString("categorySlug", categorySlug).list();
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
			T t = (T) currentSession().load(entity.getName(), id);
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
			T t = (T) session.get(entity.getName(), id);
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
	
	@Override
	@Transactional
	public IntroPost getIntroBackgroundPost() {
		String hql = "select ip from IntroPost ip where lang = :lang";
		IntroPost intro = null;
		try {
			intro = (IntroPost) currentSession().createQuery(hql).setString("lang", getPostLanguage())
												.setMaxResults(1).uniqueResult();
			if(intro == null) {
				intro = (IntroPost) currentSession().createQuery(hql).setString("lang", StringUtil.DEFAULT_LANG_CODE)
												.setMaxResults(1).uniqueResult();
			}
			return intro;
		} catch (Exception e) {
			logger.error("Greska pri dohvatanju intro pozadine. Uzrok: "+e.getMessage());
			return null;
		}		
	}
	
	@Override
	@Transactional
	public T getTranslatedPostBySlug(String slug, String lang) {
		try {
			final String post = entity.getSimpleName().toLowerCase();			
			String HQL = "select p, ip from "+i18nPost.getName()+" ip left join ip."+ post +" p"
					   + " where p.slug = :slug and ip.lang = :lang";
			
			T translated = (T) currentSession().createQuery(HQL)
					.setString("slug", slug)
					.setString("lang", lang)
					.setResultTransformer(new I18nPostResultTransformer())
					.uniqueResult();
			
			logger.info("Preveden "+ translated);
			return translated;
		} catch (Exception e) {
			logger.warn("Greska pri dohvatanju prevoda posta! "+e.getMessage());
			return null;
		}	
	}
	
	@Override
	public T getTranslatedPostById(int id, String lang) {
		try {
			final String post = entity.getSimpleName().toLowerCase();			
			String HQL = "select p, ip from "+i18nPost.getName()+" ip left join ip."+ post +" p"
					   + " where p.id = :id and ip.lang = :lang";
			
			T translated = (T) currentSession().createQuery(HQL)
					.setInteger("id", id)
					.setString("lang", lang)
					.setResultTransformer(new I18nPostResultTransformer())
					.uniqueResult();
			
			logger.info("Preveden "+ translated);
			return translated;
		} catch (Exception e) {
			logger.warn("Greska pri dohvatanju prevoda posta! "+e.getMessage());
			return null;
		}
	}
}
