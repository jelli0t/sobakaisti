package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.dao.impl.CommentDao;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

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

}
