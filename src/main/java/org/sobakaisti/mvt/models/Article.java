package org.sobakaisti.mvt.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.sobakaisti.mvt.i18n.model.I18nArticle;

@Entity
@Table(name="article")
public class Article extends Post {		

	@Lob
	@Column(name="content")
	private String content;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },targetEntity = Tag.class)
	@JoinTable(name = "article_tag", joinColumns = {
			@JoinColumn(name = "article_id")},
			inverseJoinColumns = { @JoinColumn(name = "tag_id")})
	private List<Tag> tags;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Category.class)
	@JoinTable(name="article_category", 
				joinColumns={@JoinColumn(name="article_id")}, 
				inverseJoinColumns={@JoinColumn(name="category_id")})
	private List<Category> categories;	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Media.class)
	@JoinColumn(name="featured_img_id")
	private Media featuredImage;
	
	@OneToMany(fetch = FetchType.LAZY, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = I18nArticle.class, 
        mappedBy = "article")
	private List<I18nArticle> i18nArticles;
	
	public Article() {}
	
	
	public Article(I18nArticle i18nArticle) {
		if(i18nArticle != null && i18nArticle.getArticle() != null) {
			System.out.println("new Article: "+i18nArticle.getArticle());
			this.categories = i18nArticle.getArticle().getCategories();
		}
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Media getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(Media featuredImage) {
		this.featuredImage = featuredImage;
	}
	
	
	public List<I18nArticle> getI18nArticles() {
		return i18nArticles;
	}
	public void setI18nArticles(List<I18nArticle> i18nArticles) {
		this.i18nArticles = i18nArticles;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("article: {");
		sb.append("id : " + getId() + ", ");
		sb.append(getTitle() != null ? "title : "+getTitle() + ", " : "");
		sb.append(getAuthor() != null ? "author_id : "+getAuthor().getId() + ", " : "");
		sb.append(getAuthor() != null ? "author : "+getAuthor().getFirstName() + ", " : "");
		sb.append(this.categories != null ? "categories_size : "+this.categories.size()+", " : "");
		sb.append(this.tags != null ? "tags_size : "+this.tags.size() : "");
		return sb.append("}").toString();
	}
	
	/* meta podaci za postDate */
//	public CalendarUtil getPostDateMeta() {
//		if(this.postDate != null) {
//			return new CalendarUtil(this.postDate);
//		} else {
//			return new CalendarUtil();
//		}
//	}	
}