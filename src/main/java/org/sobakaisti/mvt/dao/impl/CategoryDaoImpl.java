/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jelles
 *
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{
	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
	  

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> findAllCategories() {
		String HQL = "FROM Category";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Category> categories = session.createQuery(HQL).list();
		return categories;
	}
	
	@Override
	public Category getDefaultCategory() {
		String HQL = "FROM Category c where c.parentId = 0";
		try {
			Session session = sessionFactory.getCurrentSession();
			Category defaultCategory = (Category) session.createQuery(HQL).setMaxResults(1).uniqueResult();
			logger.info("Dohvatio podrazumevanu "+defaultCategory.toString());
			return defaultCategory;
		} catch (Exception e) {
			logger.warn("Greska prilikom dohvatanja podrazumevane kategorije! Uzrok: "+e.getMessage());
			return null;
		}		
	}

	@Override
	public Category getCategoryById(int id) {
		String HQL = "from Category c where c.id =:id";
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session.createQuery(HQL).setInteger("id", id).uniqueResult();
		return category;
	}

	@Override
	public List<Category> getAllCategoriesByIds(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		String HQL = "from Category c where c.id in (:ids)";
		try{
			@SuppressWarnings("unchecked")
			List<Category> categories = (List<Category>) session.createQuery(HQL).setParameterList("ids", ids).list();
			System.out.println("Dohvatio sam categorija size: "+categories.size());
			return categories;
		}catch (HibernateException he) {
			System.err.println("Greska pri dohvatanju kategorija: "+he.getMessage());
			return null;
		}		
	}

	@Override
	public List<Category> findAllSubcategoriesByParent(String parentSlug) {
		Session session = sessionFactory.getCurrentSession();
		String HQL = "from Category c where c.parentId = (select cin.id from Category cin where cin.slug = :parentSlug)";
		try{
			@SuppressWarnings("unchecked")
			List<Category> categories = (List<Category>) session.createQuery(HQL).setString("parentSlug", parentSlug).list();
			System.out.println("Dohvatio sam categorija size: "+categories.size());
			return categories;
		}catch (HibernateException he) {
			System.err.println("Greska pri dohvatanju kategorija: "+he.getMessage());
			return null;
		}
	}

	@Override
	public Category findCategoryBySlug(String slug) {
		final String HQL = "from Category c where c.slug =:slug";
		try {
			Session session = sessionFactory.getCurrentSession();
			Category category = (Category) session.createQuery(HQL).setString("slug", slug).uniqueResult();
			return category;
		} catch (HibernateException he) {
			return null;
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findListOfCategoriesByIds(List<Integer> ids) {
		String HQL = "from Category c where c.id in (:ids)";
		List<Category> items = new ArrayList<Category>(ids.size());
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery(HQL).setParameterList("ids", ids).list();			
		} catch (Exception e) {
			return null;
		}
		return items;
	}
}
