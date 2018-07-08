/**
 * 
 */
package org.sobakaisti.security.dao;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public User findUserByUsername(String username) {
		
		String HQL = "from User u where u.username = :username";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(HQL);
		query.setParameter("username", username);
		
		return (User) query.uniqueResult();
	}
}
