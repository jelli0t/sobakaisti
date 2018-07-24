package org.sobakaisti.mvt.models;


@Entity
@DiscriminatorValue(value = CommentOrigin.ARTICLE)
public class ArticleComment extends Comment {

  @Column(name="post_id")
  private Article article;

  //TODO getters setters
}
