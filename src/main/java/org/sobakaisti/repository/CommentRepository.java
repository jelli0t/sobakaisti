package org.sobakaisti.repository;

import org.sobakaisti.mvt.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
