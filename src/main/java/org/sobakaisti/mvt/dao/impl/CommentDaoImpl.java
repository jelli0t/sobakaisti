package org.sobakaisti.mvt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.enums.PostOrigin;
import org.sobakaisti.repository.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentDaoImpl implements CommentDao {

	private final SessionFactory sessionFactory;
	private final CommentRepository commentRepository;
	
	public Comment findById(int id) {
		return commentRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	@Override
	public List<Comment> getPostComments(int postId, PostOrigin postOrigin, int from, int max) {
		List<Comment> comments = null;
		String HQL = "from Comment c where c.enabled = true and c.postId = :postId and c.commentOrigin = :origin order by c.id desc";
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
	public int countCommentsPerPost(int postId, PostOrigin postOrigin) {
		String HQL = "select count(c.id) from Comment c where c.enabled = true and c.postId = :postId and c.commentOrigin = :origin";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("postId", postId);
			query.setParameter("origin", postOrigin);
			Long counted = (Long) query.uniqueResult();			
			if(counted != null) {
				log.info("Za post sa id:"+postId+" sam prebrojao "+counted +" komentara.");
				return counted.intValue();
			}
		} catch (Exception e) {
			log.warn("Greska prilikom brojanaj komentara za post sa id:"+postId+". Uzrok: "+e.getMessage());
		}	
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsBundle(int postId, PostOrigin postOrigin, int from, int size) {
		List<Comment> comments = null;
		String HQL = "from Comment c where c.enabled = true and c.postId = :postId and c.commentOrigin = :origin order by c.id desc";
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(HQL);
			query.setParameter("postId", postId);
			query.setParameter("origin", postOrigin);
			query.setFirstResult(from);		
			query.setMaxResults(size);			
			comments = query.list();
			log.info("Dohvatio sam grupu od "+comments.size()+ " komentara.");
		} catch (Exception e) {
			log.warn("Greska prilikom dohvatanja grupe komentara. Uzrok: "+e.getMessage());
		}		
		return comments;
	}
	
	@Override
	public Map<Integer, Integer> generatePostIdToCommentsCountMap(PostOrigin postOrigin) {
		Map<Integer, Integer> postToCommentsNum = null;
		String HQL = "select c.postId, count(c.id) from Comment c where c.enabled = true and c.commentOrigin = :origin group by c.postId";
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
			log.info("Generisao sam mapu post_id => comments_coun za "+postToCommentsNum.size()+" postova tipa: "+postOrigin+".");
		} catch (Exception e) {
			log.warn("Dogodila se greska prilikom generisanja mape[post_id => comments_coun]. Uzrok: "+e.getMessage());
			postToCommentsNum = new HashMap<Integer, Integer>(0);
		}		
		return postToCommentsNum;
	}
}
