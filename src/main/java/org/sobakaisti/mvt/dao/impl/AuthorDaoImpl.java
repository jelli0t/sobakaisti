/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jelles
 *
 */
@Repository
public class AuthorDaoImpl implements AuthorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
//	public AuthorDaoImpl(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	@Override
	public Author getAuthorById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<Author> getAllAuthors() {
		final String HQL = "FROM Author";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Author> authors = session.createQuery(HQL).list();
		return authors;
	}

	@Override
	@Transactional
	public void persistAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession();
		session.save(author);
	}

	
}
