/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.PublicationDao;
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

}
