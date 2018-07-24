package org.sobakaisti.mvt.models;


@Entity
@DiscriminatorValue(value = CommentOrigin.ARTICLE)
public class ArticleComment extends Comment {



}
