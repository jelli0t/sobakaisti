/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author NEKS Office
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
			System.out.println("Pronasao sam "+count+" "+(isActive ? "aktivnih" : "neaktivnih")+ " publikacija.");
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
	
}
