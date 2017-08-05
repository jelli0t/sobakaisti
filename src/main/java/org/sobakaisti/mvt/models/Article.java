package org.sobakaisti.mvt.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Size(min=2, max=120, message="{validation.warn.title.size}")
	@Column(name="title")
	private String title;
	
	@Lob
	@Column(name="content")
	private String content;
	@Column(name="slug")
	private String slug;
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar postDate;
	
	@Column(name="lang")
	private String lang;
	
	@Column(name="active")
	private int active;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Author.class)
	private Author author;
	
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
	
	@Column(name="featured_img")
	private String featuredImage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Calendar getPostDate() {
		return postDate;
	}
	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
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
	public String getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}
	
}