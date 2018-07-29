package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;

public interface CommentService {

  public Comment populateAndSave(Comment comment, Class<? extends Post> postType);

  List<Comment> findPostComments(int postId, Origin postOrigin, int from, int max);
}
