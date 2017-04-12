package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.IntroArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public String getArticleById(int id) {
		String HQL = "FROM Article WHERE id=:id";
		Session session = sessionFactory.getCurrentSession();
		Article article = (Article) session.createQuery(HQL).setInteger("id", id).uniqueResult();
		String content = article.getContent();
		if(content != null){
			return content;
		}else{
			return "";
		}
	}
	
	@Override
	@Transactional
	public Article findArticleById(int id) {
		String HQL = "FROM Article WHERE id=:id";
		try{
			Session session = sessionFactory.getCurrentSession();
			Article article = (Article) session.createQuery(HQL).setInteger("id", id).uniqueResult();
			return article;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public String getintroArticleByLanguage(String langCode) {
		String HQL = "FROM IntroArticle ia WHERE ia.lang=:langCode";
		Session session = sessionFactory.getCurrentSession();
		IntroArticle article = (IntroArticle) session.createQuery(HQL).setString("langCode", langCode).uniqueResult();
		String content = article.getContent();
		if(content != null){
			return content;
		}else{
			return "";
		}
	}

	@Override
	@Transactional
	public Article getArticleBySlugTitle(String slug, String lang) {
		String HQL = "FROM Article WHERE slug=:slug and lang=:langCode";
		try{
			Session session = sessionFactory.getCurrentSession();
			Article article = (Article) session.createQuery(HQL)
					.setString("slug",slug)
					.setString("langCode", lang).uniqueResult();
			return article;
		}catch (HibernateException he) {
			// TODO: handle exception
			return null;
		}		
	}

	@Override
	@Transactional
	public Article saveArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(article);
			return article;
		}catch (HibernateError he) {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getArticlesSortedByDate() {
		String HQL = "FROM Article a WHERE a.postDate is not null order by date(a.postDate) desc";
		Session session = sessionFactory.getCurrentSession();
		List<Article> articles = session.createQuery(HQL).list();
		return articles;
	}

	@Override
	@Transactional
	public boolean deleteArticleById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Article articleToRemove = (Article) session.load(Article.class, id);
				
		if(articleToRemove != null){
			session.delete("article", articleToRemove);
			return true;
		}
		return false;
	}

}
