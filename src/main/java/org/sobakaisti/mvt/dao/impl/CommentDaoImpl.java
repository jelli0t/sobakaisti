/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;
import org.sobakaisti.mvt.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author jelli0t
 *
 */
@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

	private static final Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public Comment findById(int id) {
		Comment comment = null;
		try {
			String HQL = "from Comment c where c.id = :id";
			Session session = sessionFactory.getCurrentSession();
			comment = (Comment) session.createQuery(HQL).setParameter("id", id).uniqueResult();			
		} catch (Exception e) {
			
		}
		return comment;
	}
	
	@Override
	public Comment save(Comment comment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(comment);
			return comment;
		} catch (Exception e) {
//			logger.warn("Greska pri cuvanju enititeta. Uzrok: "+e.getMessage());
			return null;
		}	
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getPostComments(int postId, Post.Origin postOrigin, int from, int max) {
		List<Comment> comments = null;
		String HQL = "from Comment c where c.enabled = 1 and c.postId = :postId and c.commentOrigin = :origin order by c.id desc";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("postId", postId);
			query.setParameter("origin", postOrigin);
			query.setFirstResult(from);			
			if(max != 0)
				query.setMaxResults(max);
			
			comments = query.list();			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return comments;
	}

	@Override
	public int countCommentsPerPost(int postId, Origin postOrigin) {
		String HQL = "select count(c.id) from Comment c where c.enabled = 1 and c.postId = :postId and c.commentOrigin = :origin";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("postId", postId);
			query.setParameter("origin", postOrigin);
			Long counted = (Long) query.uniqueResult();			
			if(counted != null) {
				logger.info("Za post sa id:"+postId+" sam prebrojao "+counted +" komentara.");
				return counted.intValue();
			}
		} catch (Exception e) {
			logger.warn("Greska prilikom brojanaj komentara za post sa id:"+postId+". Uzrok: "+e.getMessage());
		}	
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsBundle(int postId, Origin postOrigin, int from, int size) {
		List<Comment> comments = null;
		String HQL = "from Comment c where c.enabled = 1 and c.postId = :postId and c.commentOrigin = :origin order by c.id desc";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("postId", postId);
			query.setParameter("origin", postOrigin);
			query.setFirstResult(from);		
			query.setMaxResults(size);			
			comments = query.list();
			logger.info("Dohvatio sam grupu od "+comments.size()+ " komentara.");
		} catch (Exception e) {
			logger.warn("Greska prilikom dohvatanja grupe komentara. Uzrok: "+e.getMessage());
		}		
		return comments;
	}
	
	@Override
	public Map<Integer, Integer> generatePostIdToCommentsCountMap(Origin postOrigin) {
		Map<Integer, Integer> postToCommentsNum = null;
		String HQL = "select c.postId, count(c.id) from Comment c where c.enabled = 1 and c.commentOrigin = :origin group by c.postId";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("origin", postOrigin);
			@SuppressWarnings("unchecked")
			List<Object[]> rawResult = query.list();
			if(rawResult != null) {
				postToCommentsNum = new HashMap<Integer, Integer>(rawResult.size());
				for(Object[] row : rawResult)
					if(row.length == 2)
						postToCommentsNum.put((Integer) row[0], ((Long) row[1]).intValue());
			}
			logger.info("Generisao sam mapu post_id => comments_coun za "+postToCommentsNum.size()+" postova tipa: "+postOrigin+".");
		} catch (Exception e) {
			logger.warn("Dogodila se greska prilikom generisanja mape[post_id => comments_coun]. Uzrok: "+e.getMessage());
			postToCommentsNum = new HashMap<Integer, Integer>(0);
		}		
		return postToCommentsNum;
	}
}
