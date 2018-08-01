package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;

public interface CommentService {
	
	public static final String COMMENTS_COUNT_MODEL_ATTRIBUTE_NAME = "COMMENTS_COUNT";

  public Comment populateAndSave(Comment comment, Class<? extends Post> postType);

  List<Comment> findPostComments(int postId, Origin postOrigin, int from, int max);

  /**
   * Count comments per post
   * */
  int countPostComments(int postId, Origin postOrigin);
}
