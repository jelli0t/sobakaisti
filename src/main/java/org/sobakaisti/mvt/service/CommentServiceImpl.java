package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


  public Comment populateAndSave(Comment comment, Class<? extends Post> postType) {
    if(comment != null) {
          Origin origin = Post.Origin.getByEntityType(postType);
          
          comment.setCommentOrigin(origin);
          
          if(comment.isAuthenticatedAuthor()) {
            //TODO call userService
          }
    }
    return comment;
  }
  
  
}
