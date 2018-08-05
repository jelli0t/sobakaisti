package org.sobakaisti.mvt.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.impl.CommentDao;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public List<Comment> findPostComments(int postId, Origin postOrigin, int from, int max) {
		return commentDao.getPostComments(postId, postOrigin, from, max);
	}

	@Override
	public Comment populateAndSave(Comment comment, Class<? extends Post> postType) {
		if (comment != null) {
			Origin origin = Post.Origin.getByEntityType(postType);

			comment.setCommentOrigin(origin);

			if (comment.isAuthenticatedAuthor()) {
				// TODO call userService
			}
		}
		return commentDao.save(comment);
	}
	
	@Override
	public Comment authorizeAndSave(Comment comment) {
		logger.info("Autorizujemo komentar na post tipa: "+comment.getCommentOrigin()+" sa id: "+comment.getPostId());
		if (comment.isAuthenticatedAuthor()) {
			// TODO autorizuj ako je registrovan korisnik.
		}
		return commentDao.save(comment);
	}
	
	@Override
	public int countPostComments(int postId, Origin postOrigin) {
		return commentDao.countCommentsPerPost(postId, postOrigin);
	}

	@Override
	public List<Comment> commentsBundleLoad(int postId, Origin postOrigin, int loaded, int size) {
		int total = countPostComments(postId, postOrigin);		
		if(loaded == total) {
			logger.info("Vec su ucitani svi komentari za post:"+postId+". Nemam sta da dohvatim!");
			return null;
		}		
		size = loaded+size > total ? total-loaded : size;
		logger.info("Pozivan ucitavanje grupe od "+size+" komentara za post: "+postId);
		return commentDao.getCommentsBundle(postId, postOrigin, loaded, size);
	}
	
	@Override
	public Map<Integer, Integer> getPostToCommentsCountMap(Origin postOrigin) {
		return commentDao.generatePostIdToCommentsCountMap(postOrigin);
	}
}
