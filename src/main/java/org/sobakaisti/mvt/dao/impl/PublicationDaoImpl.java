/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jelli0t
 *
 */
@Repository
@Transactional
public class PublicationDaoImpl implements PublicationDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Publication> findAllOrderedPublications() {
		String HQL = "FROM Publication p WHERE p.postDate is not null and p.active = 1 order by date(p.postDate) desc, p.id desc";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Publication> publications = session.createQuery(HQL).list();
		return publications;
	}

	@Override
	public List<Publication> findAllPublicationByAuthor(Author author) {
		try {
			String HQL = "FROM Publication p WHERE p.author = :author and p.postDate is not null and p.active = 1 order by date(p.postDate) desc, p.id desc";
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Publication> publications = session.createQuery(HQL).setParameter("author", author).list();
			return publications;
		} catch (Exception e) {
			return null;
		}		
	}
	
	@Override
	public boolean deletePublicatinById(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Publication publicationToDelete = (Publication) session.get(Publication.class, id);
			if(publicationToDelete != null) {
				session.delete(publicationToDelete);
				return true;
			} else {
				session.close();
				return false;
			}			
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int switchPublicationStatus(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Publication publication = (Publication) session.load(Publication.class, id);
			if(publication != null){
				int active = publication.getActive();
				switch (active) {
				case 0:
					publication.setActive(1);			
					break;
				case 1:
					publication.setActive(0);
					break;
				default:
					return -1;
				}
				session.update("publication", publication);
				return publication.getActive();
			}else {
				return -1;
			}
		}catch (Exception e) {
			return -1;
		}		
	}

	//TODO napravi metodu univerzalnom i podigni je u superklasu
	@Override
	public int countPublicationsByStatus(boolean isActive) {
		int count = 0;
		String HQL = "select count(p.id) from Publication p where p.active = :status";
		try {
			Session session = sessionFactory.getCurrentSession();
			int status = isActive ? 1 : 0;
			Long result = (Long) session.createQuery(HQL).setInteger("status", status).uniqueResult();
			count = result.intValue();
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}
	
	@Override
	public List<Publication> findAllPublicationsByStatus(int status) {
		String HQL = "FROM Publication p WHERE p.postDate is not null and p.active = :status order by date(p.postDate) desc, p.id desc";	
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Publication> publications = session.createQuery(HQL).setInteger("status", status).list();
			return publications;
		} catch (Exception e) {
			return null;
		}		
	}

	@Override
	public int countSlugDuplicates(String slug) {
		int count = 0;
		String HQL = "select count(p.id) from Publication p where p.slug like :slug";
		try {
			Session session = sessionFactory.getCurrentSession();
			Long result = (Long) session.createQuery(HQL).setString("slug", slug+"%").uniqueResult();
			count = result.intValue();
			System.out.println("Pronasao sam "+count+" duplikata slug-ova na Publication");
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}

	@Override
	public boolean savePublication(Publication publication) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save("publication", publication);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	public List<Author> findAllPublicationsAuthors() {
		String HQL = "select distinct p.author from Publication p where p.active = 1";	
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
	public Pagination createPostPagination(Pagination pagination, PostFilter filter) {
		String HQL = "select count(p) from Publication p where p.postDate is not null"
					+(filter.isActive() ? " and p.active = 1" : " and p.active = 0")
					+(filter.isNonactiveInlude() ? " or p.active = 0" : "") 
					+(filter.hasAuthor() ? " and p.author = :author" : "")
					+" order by date(p.postDate) desc, p.id desc";
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
}
