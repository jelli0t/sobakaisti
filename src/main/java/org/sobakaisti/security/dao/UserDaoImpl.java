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
import org.sobakaisti.util.TextUtil;
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
	
	@Override
	public boolean checkIfUserExists(String username, String email) {		 
		if(TextUtil.isEmpty(username) & TextUtil.isEmpty(email))
			return false;
		
		String HQL_QUERY = "select 1 from org.sobakaisti.security.model.User u where 1=1"
			+ (TextUtil.notEmpty(username) ? " and u.username = :username" : "")
			+ (TextUtil.notEmpty(email) ? " and u.email = :email" : "");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(HQL_QUERY);

			if(TextUtil.notEmpty(username))
			   query.setParameter("username", username);		
			if(TextUtil.notEmpty(email))
			   query.setParameter("email", email);

			Integer result = (Integer) query.uniqueResult();
			return (result != null && result.intValue() == 1);   
		} catch(Exception ex) {
			return false;
		}
	}
}
