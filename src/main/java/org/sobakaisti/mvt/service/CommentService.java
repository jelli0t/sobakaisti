package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;

public interface CommentService {

  public Comment populateAndSave(Comment comment, Class<? extends Post> postType);
}
