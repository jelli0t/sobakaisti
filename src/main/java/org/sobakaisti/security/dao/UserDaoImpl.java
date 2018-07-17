/**
 * 
 */
package org.sobakaisti.security.dao;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.sobakaisti.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public User findUserByUsername(String username) {		
		String HQL = "from User u where u.username = :username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(HQL);
		query.setParameter("username", username);
		
		return (User) query.uniqueResult();
	}
	
	@Override
	public long countAllUsers() {
		long count = 0;
		Session session = sessionFactory.getCurrentSession();		
		Number rowCount = (Number) session.createCriteria("org.sobakaisti.security.model.User").setProjection(Projections.rowCount()).uniqueResult();
		if(rowCount != null)
			count = rowCount.longValue();			
		return count;
	}
}
