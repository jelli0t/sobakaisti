package org.sobakaisti.mvt.service;

public interface CommentService {

  public Comment populateAndSave(Comment comment, Class postType);
}
