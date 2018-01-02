/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);	

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Author getAuthorById(int id) {
		final String HQL = "from Author where id=:id";
		Session session = sessionFactory.getCurrentSession();
		Author author = (Author) session.createQuery(HQL).setInteger("id", id).uniqueResult();
		if(author != null){
			return author;
		}else{
			return null;
		}		
	}	
	
	@Override
	@Transactional
	public List<Author> getAllAuthors() {
		final String HQL = "FROM Author";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Author> authors = session.createQuery(HQL).list();
		logger.info("Dohvatio listu od "+authors.size()+" autora.");
		return authors;
	}

	@Override
	@Transactional
	public void persistAuthor(Author author) {
		Session session = sessionFactory.getCurrentSession();
		session.save(author);
	}

	@Override
	@Transactional
	public void deleteAuthor(int id) throws HibernateException{
		Session session = sessionFactory.getCurrentSession();
		String HQL = "delete from Author where id =:ID"; 
		int result = session.createQuery(HQL).setInteger("ID", id).executeUpdate();
	}

	@Override
	@Transactional
	public Author findAuthorBySlug(String slug) {
		Session session = sessionFactory.getCurrentSession();
		String HQL = "from Author where slug =:slug"; 
		try {
			Author author = (Author) session.createQuery(HQL).setString("slug", slug).uniqueResult();
			return author;
		} catch (Exception e) {
			return null;
		}		
	}
}
