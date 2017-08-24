package org.sobakaisti.mvt.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.IntroArticle;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
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
	public Article findArticleBySlug(String slug) {
		String hql = "from Article a where a.slug = :slug";
		try{
			Session session = sessionFactory.getCurrentSession();
			Article article = (Article) session.createQuery(hql).setString("slug", slug).uniqueResult();
			return article;
		}catch (HibernateException he) {
			System.err.println("Greska: "+he.getMessage());
			return null;
		}	
	}

	@Override
	@Transactional
	public boolean saveArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(article);
			return true;
		}catch (HibernateError he) {
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getArticlesSortedByDate(int resultsLimit) {
		String HQL = "FROM Article a WHERE a.postDate is not null order by date(a.postDate) desc, a.id desc";
		Session session = sessionFactory.getCurrentSession();
		List<Article> articles = session.createQuery(HQL).list();
		return articles;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getArticlesSortedByDate(int index, int resultsLimit) {
		String HQL = "FROM Article a WHERE a.postDate is not null order by date(a.postDate) desc, a.id desc";
		Session session = sessionFactory.getCurrentSession();
		List<Article> articles = session.createQuery(HQL)
										.setFirstResult(index)
										.setMaxResults(resultsLimit).list();
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

	@Override
	@Transactional
	public int switchArticleStatus(int articleId) {
		Session session = sessionFactory.getCurrentSession();
		Article article = (Article) session.load(Article.class, articleId);
		if(article != null){
			int active = article.getActive();
			switch (active) {
			case 0:
				article.setActive(1);				
				break;
			case 1:
				article.setActive(0);
				break;
			default:
				return -1;
			}
			session.update("article", article);
			return article.getActive();
		}
		return -1;
	}

	//TODO napravi metodu univerzalnom i podigni je u superklasu
	@Override
	@Transactional
	public int countArticlesByStatus(boolean isActive) {
		int count = 0;
		String HQL = "select count(a.id) from Article a where a.active = :status";
		try {
			Session session = sessionFactory.getCurrentSession();
			int status = isActive ? 1 : 0;
			Long result = (Long) session.createQuery(HQL).setInteger("status", status).uniqueResult();
			count = result.intValue();
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}
	
	@Override
	@Transactional
	public List<Article> findAllArticlesByStatus(int status) {
		String HQL = "FROM Article a WHERE a.postDate is not null and a.active = :status order by date(a.postDate) desc, a.id desc";	
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Article> articles = session.createQuery(HQL).setInteger("status", status).list();
			System.out.println("Pronsao "+articles.size()+" clanaka sa statusom: "+status);
			return articles;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<Article>(0);
		}		
	}
	
	@Override
	@Transactional
	public List<Author> findAllArticlesAuthorsByCategory(Category category) {		
		String hql = "select distinct ar.author from Article ar where ar.id in (select a.id from Article a join a.categories c where c.id = :id and a.active = 1)";
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Author> authors = session.createQuery(hql).setInteger("id", category.getId()).list();	//.setParameterList("categories", categories).list();
			System.out.println("Dohvatio sam: "+authors.size()+" autora za "+category.getName()+" katekoriju!");
			return authors;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<Author>(0);
		}
	}
	
	@Override
	@Transactional
	public List<Article> findAllArticlesForCategory(Category category, boolean isActive) {
		String HQL = "select a from Article a join a.categories c where c.id = :id and a.active = :active"
					+" and a.postDate is not null order by date(a.postDate) desc, a.id desc";
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Article> articles = session.createQuery(HQL)
									.setInteger("id", category.getId())
									.setInteger("active", isActive ? 1 : 0)
									.list();
			System.out.println("Dohvatio sam: "+articles.size()+" clanaka za "+category.getName()+" katekoriju!");
			
			return articles;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<Article>(0);
		}
	}
	
	@Override
	@Transactional
	public List<Article> findAriclesBundleByCategory(Category category, int from, int size, boolean isActive) {
		String HQL = "select a from Article a join a.categories c where c.id = :id"
				+(isActive ? " and a.active = :active" : "")
				+" and a.postDate is not null order by date(a.postDate) desc, a.id desc";
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Article> articles = session.createQuery(HQL)
									.setInteger("id", category.getId())
									.setInteger("active", isActive ? 1 : 0)
									.setFirstResult(from)
									.setMaxResults(size)
									.list();
			System.out.println("Dohvatio sam: "+articles.size()+" clanaka za "+category.getName()+" katekoriju!");
			
			return articles;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<Article>(0);
		}
	}
	
	@Override
	@Transactional
	public List<Article> findArticlesBundleForCategoryByAuthor(Category category, Author author, int from, int size,
			boolean isActive) {
		String HQL = "select a from Article a join a.categories c where c.id = :id"
				+(author != null ? " and a.author = :author" : "")
				+(isActive ? " and a.active = :active" : "")
				+" and a.postDate is not null order by date(a.postDate) desc, a.id desc";
		try {
			Session session = sessionFactory.getCurrentSession();			
			Query query = session.createQuery(HQL);			
			query.setInteger("id", category.getId());
			if(author != null)
				query.setParameter("author", author);
			query.setInteger("active", isActive ? 1 : 0);
			@SuppressWarnings("unchecked")
			List<Article> articles = query.setFirstResult(from).setMaxResults(size).list();
			
			System.out.println("Dohvatio sam: "+articles.size()+" clanaka od "+author.getLastName()+", za "+category.getName()+" katekoriju!");
			
			return articles;
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
			return new ArrayList<Article>(0);
		}
	}
	
	@Override
	@Transactional
	public int countSlugDuplicates(String slug) {
		int count = 0;
		String HQL = "select count(a.id) from Article a where a.slug like :slug";
		System.out.println("Pokusavam da prebrojim br duplikata za slug: "+slug);
		try {
			Session session = sessionFactory.getCurrentSession();
			Long result = (Long) session.createQuery(HQL).setString("slug", slug+"%").uniqueResult();
			count = result.intValue();
			System.out.println("Pronasao sam "+count+" duplikata slug-ova na Artiklima");
		} catch (Exception e) {
			System.out.println("Uhvati exception: "+e.getMessage());
			return count;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> findRelatedLatestArticles(Article exclude, int size) {
		String selectByAuthor = "from Article a where a.author.id = :authorId and a.id <> :id order by date(a.postDate) desc";
		String selectByCategoy = "select a from Article a join a.categories c where c.id = :cid and a.id != :aid and a.author.id <> :authorId order by date(a.postDate) desc";
		
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Article> relatedByAuthor = (List<Article>) session.createQuery(selectByAuthor)
											.setInteger("authorId", exclude.getAuthor().getId())
											.setInteger("id", exclude.getId())
											.setMaxResults(size/2).list();

			int founded = relatedByAuthor.size();
			int secondMaxResult = founded < (size/2) ? size-founded : size/2;
			
			System.out.println("relatedByAuthor size: "+founded);
			
			List<Article> relatedByCat = session.createQuery(selectByCategoy)
										.setInteger("cid", exclude.getCategories().get(0).getId())
										.setInteger("aid", exclude.getId())
										.setInteger("authorId", exclude.getAuthor().getId())
										.setMaxResults(secondMaxResult).list();
			
			System.out.println("relatedByCat size: "+relatedByCat.size());
			
			if(relatedByAuthor != null && !relatedByAuthor.isEmpty()) {
				relatedByAuthor.addAll(relatedByCat);
				return relatedByAuthor;
			}else {
				return relatedByCat;
			}			
		} catch (Exception e) {
			System.err.println("Greska pri dvostrukom selektu: "+e.getMessage());
			return null;
		}			
	}
	
	@Override
	@Transactional
	public List<Article> findNextAndPreviousArticle(Article article){
		Article nextArticle = new Article();
		Article previousArticle = new Article();
		List<Article> bothArticles = new ArrayList<Article>(2);
		
		String nextSelect = "select a from Article a join a.categories c where a.id <> :aid "
				+ "and (a.author.id = :authorId or c.id = :cid) and a.postDate >= :postDate and active = 1 order by a.postDate asc";
		String prevSelect = "select a from Article a join a.categories c where a.id <> :aid "
				+ "and (a.author.id = :authorId or c.id = :cid) and a.postDate <= :postDate and active = 1 order by a.postDate desc";
	
		try {
			Session session = sessionFactory.getCurrentSession();			
			nextArticle = (Article) session.createQuery(nextSelect).setInteger("aid", article.getId())
						.setInteger("authorId", article.getAuthor().getId())
						.setInteger("cid", article.getCategories().get(0).getId())
						.setCalendar("postDate", article.getPostDate())
						.setMaxResults(1).uniqueResult();
			previousArticle = (Article) session.createQuery(prevSelect).setInteger("aid", article.getId())
						.setInteger("authorId", article.getAuthor().getId())
						.setInteger("cid", article.getCategories().get(0).getId())
						.setCalendar("postDate", article.getPostDate())
						.setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		bothArticles.add(0, nextArticle);
		bothArticles.add(1, previousArticle);
		return bothArticles;
	}
	
	
}
