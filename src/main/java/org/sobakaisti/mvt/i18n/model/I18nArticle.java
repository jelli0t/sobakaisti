/**
 * 
 */
package org.sobakaisti.mvt.i18n.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.sobakaisti.mvt.models.Article;

/**
 * @author jelli0t
 *
 */
@Entity
@Table(name="i18n_article")
public class I18nArticle extends I18nPost {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =
	{
	        CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST
	},
	targetEntity = Article.class)
	@JoinColumn(name="article_id")
	private Article article;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}