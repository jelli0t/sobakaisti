package org.sobakaisti.mvt.dao.impl;

import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.repository.AuthorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
	
	private final SessionFactory sessionFactory;
	private final AuthorRepository authorRepository;
	
	@Override
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Override
	public List<Author> getAllAuthors() {
		return (List<Author>) authorRepository.findAll();
	}

	@Override
	@Transactional
	public Author findFull(int id) {
		Author author = null;
		String HQL = "from Author a left join fetch a.profile where a.id = :id";
		try {
			Session session = sessionFactory.getCurrentSession();
			author = (Author) session.createQuery(HQL).setParameter("id", id).uniqueResult();
		} catch (Exception e) {
			log.warn("Dogodila se greska prilikom dohvatanja autora sa ID:"+id+". Uzork: "+e.getMessage());
		}
		return author;
	}

	@Override
	@Transactional
	public Author findFull(String slug) {
		Author author = null;
		String HQL = "from Author a join fetch a.profile where a.slug = :slug";
		try {
			Session session = sessionFactory.getCurrentSession();
			author = (Author) session.createQuery(HQL).setParameter("slug", slug).uniqueResult();
		} catch (Exception e) {
			log.warn("Dogodila se greska prilikom dohvatanja autora: "+slug+". Uzork: "+e.getMessage());
		}
		return author;
	}
	
	@Override
	@Transactional
	public boolean saveOrUpdate(Author author) {
		return authorRepository.save(author) != null;
	}
	
	@Override
	@Transactional
	public void persistAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	@Transactional
	public void deleteAuthor(int id) throws HibernateException{
		Session session = sessionFactory.getCurrentSession();
		String HQL = "delete from Author where id =:ID"; 
		int result = session.createQuery(HQL).setInteger("ID", id).executeUpdate();
	}

	@Override
	public Author findAuthorBySlug(String slug) {
		return authorRepository.findBySlug(slug)
				.orElseThrow(() -> new EntityNotFoundException());
	}
}
