/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author jelli0t
 *
 */
@MappedSuperclass
public abstract class Post {
	
	public static final int ACTIVE = 1;
	public static final int NONACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Size(min=2, max=120, message="{validation.warn.title.size}")
	@Column(name="title")
	private String title;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar postDate;	

	@Column(name="lang")
	private String lang;
	
	@Column(name="active")
	private int active;
	
	@Valid
	@ManyToOne(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Author.class)
	private Author author;
	
	@Transient
	private Boolean commited;
	@Transient
	private String commitMessage;	
	
		
	public Post() {
		super();
	}

	public Post(int id, String title, String slug, Calendar postDate, String lang, int active, Author author) {
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.postDate = postDate;
		this.lang = lang;
		this.active = active;
		this.author = author;
	}
	
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Calendar getPostDate() {
		return postDate;
	}
	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
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
	public Boolean getCommited() {
		return commited;
	}
	public void setCommited(Boolean commited) {
		this.commited = commited;
	}
	public String getCommitMessage() {
		return commitMessage;
	}
	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
}
