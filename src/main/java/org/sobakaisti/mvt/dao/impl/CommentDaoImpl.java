/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jelli0t
 *
 */
@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

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
	
}
