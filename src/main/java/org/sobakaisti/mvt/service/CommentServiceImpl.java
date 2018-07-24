package org.sobakaisti.mvt.service;

@Service
public class CommentServiceImpl implements CommentService {


  public Comment populateAndSave(Comment comment, Class postType) {
    if(comment != null) {
          Origin origin = Post.Origin.getByEntityType(postType);
          
          if(comment.isAuthenticatedUser()) {
            //TODO call userService
          }
    }
    return comment;
  }
  
  
}
