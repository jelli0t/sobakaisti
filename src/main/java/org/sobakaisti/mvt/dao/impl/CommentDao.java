package org.sobakaisti.mvt.dao.impl;

import java.util.List;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post.Origin;

public interface CommentDao {

	List<Comment> getPostComments(int postId, Origin postOrigin, int from, int max);

	Comment save(Comment comment);
	/**
	 * Vraca broj aktivnih komentara na postu
	 * */
	public int countCommentsPerPost(int postId, Origin postOrigin);

}
