package org.sobakaisti.mvt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AbstractPostDao;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.IntroArticle;
import org.sobakaisti.mvt.service.impl.ArticleServiceImpl;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ArticleDaoImpl extends AbstractPostDao<Article> implements ArticleDao {
	private static final Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

	@Override
	@Transactional
	public String getintroArticleByLanguage(String langCode) {
		String HQL = "FROM IntroArticle ia WHERE ia.lang=:langCode";
		IntroArticle article = (IntroArticle) currentSession().createQuery(HQL).setString("langCode", langCode).uniqueResult();
		String content = article.getContent();
		if(content != null){
			return content;
		}else{
			return "";
		}
	}
	
	@Override
	@Transactional
	public String findIntroArticle(String langCode) {
		String HQL = "FROM Article a WHERE a.slug = :slug and a.lang=:langCode";
		try {
			Article introArticle = (Article) currentSession().createQuery(HQL)
									.setString("slug", ArticleDao.INTRO_ARTICLE_SLUG)
									.setString("langCode", langCode)
									.uniqueResult();
			if(introArticle != null) {
				return introArticle.getContent();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Article> getArticlesSortedByDate(Pagination pagination, PostFilter filter) {
		List<Article> articles = null;
		logger.info(filter.toString());
		String HQL = "from Article a where a.postDate is not null" 
					 +(filter.isActive() ? " and a.active = 1" : " and a.active = 0")
					 +(filter.isNonactiveInlude() ? " or a.active = 0" : "")
					 +(filter.hasAuthor() ? " and a.author = :author" : "")
					 +" order by date(a.postDate) desc, a.id desc";
		logger.info("Query: "+HQL);
		try {
			Query query = currentSession().createQuery(HQL);
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			articles = query.setFirstResult(pagination.getInitialItem())
							.setMaxResults(pagination.getItemsPerPage())
							.list();
			logger.info("Dohvatio sam listu od "+articles.size()+ " clanaka.");
		} catch (Exception e) {
			articles = new ArrayList<Article>(0);
			logger.warn("Greska prilikom dohvatanja postova iz baze. Uzrok: "+e.getMessage());
		}		
		return articles;
	}
	
	
	@Override
	@Transactional
	public Pagination createPostPagination(Pagination pagination, PostFilter filter) {
		String HQL = "select count(a) from Article a where a.postDate is not null"
					+(filter.isActive() ? " and a.active = 1" : " and a.active = 0")
					+(filter.isNonactiveInlude() ? " or a.active = 0" : "") 
					+(filter.hasAuthor() ? " and a.author = :author" : "")
					+" order by date(a.postDate) desc, a.id desc";
		try {
			Query query = currentSession().createQuery(HQL);
			if(filter.hasAuthor())
				query.setParameter("author", filter.getAuthor());
			Long count = (Long) query.uniqueResult();
			pagination.setMaxItems(count.intValue());
			System.out.println("Ima ukupno "+count.longValue()+" postova");
		} catch (Exception e) {
			System.err.println("Greska pri brojanju postova! "+e.getMessage());
		}
		return pagination;
	}

	//TODO napravi metodu univerzalnom i podigni je u superklasu
	@Deprecated
	@Override
	@Transactional
	public int countArticlesByStatus(boolean isActive) {
		int count = 0;
		String HQL = "select count(a.id) from Article a where a.active = :status";
		try {
			int status = isActive ? 1 : 0;
			Long result = (Long) currentSession().createQuery(HQL).setInteger("status", status).uniqueResult();
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
			@SuppressWarnings("unchecked")
			List<Article> articles = currentSession().createQuery(HQL).setInteger("status", status).list();
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
			@SuppressWarnings("unchecked")
			List<Author> authors = currentSession().createQuery(hql).setInteger("id", category.getId()).list();	//.setParameterList("categories", categories).list();
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
			@SuppressWarnings("unchecked")
			List<Article> articles = currentSession().createQuery(HQL)
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
			@SuppressWarnings("unchecked")
			List<Article> articles = currentSession().createQuery(HQL)
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
			Query query = currentSession().createQuery(HQL);			
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
	
	@Deprecated
	@Override
	@Transactional
	public int countSlugDuplicates(String slug) {
		int count = 0;
		String HQL = "select count(a.id) from Article a where a.slug like :slug";
		System.out.println("Pokusavam da prebrojim br duplikata za slug: "+slug);
		try {
			Long result = (Long) currentSession().createQuery(HQL).setString("slug", slug+"%").uniqueResult();
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
		String selectByAuthor = "from Article a where a.author.id = :authorId and a.id <> :id order by date(a.postDate) desc, a.id desc";
		String selectByCategoy = "select a from Article a join a.categories c where c.id = :cid and a.id != :aid and a.author.id <> :authorId order by date(a.postDate) desc, a.id desc";
		
		try {
			List<Article> relatedByAuthor = (List<Article>) currentSession().createQuery(selectByAuthor)
											.setInteger("authorId", exclude.getAuthor().getId())
											.setInteger("id", exclude.getId())
											.setMaxResults(size/2).list();

			int founded = relatedByAuthor.size();
			int secondMaxResult = founded < (size/2) ? size-founded : size/2;
			
			System.out.println("relatedByAuthor size: "+founded);
			
			List<Article> relatedByCat = currentSession().createQuery(selectByCategoy)
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
			nextArticle = (Article) currentSession().createQuery(nextSelect).setInteger("aid", article.getId())
						.setInteger("authorId", article.getAuthor().getId())
						.setInteger("cid", article.getCategories().get(0).getId())
						.setCalendar("postDate", article.getPostDate())
						.setMaxResults(1).uniqueResult();
			previousArticle = (Article) currentSession().createQuery(prevSelect).setInteger("aid", article.getId())
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