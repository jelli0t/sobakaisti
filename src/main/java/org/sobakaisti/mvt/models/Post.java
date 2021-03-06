/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.Access;
import javax.persistence.AccessType;
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

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.util.TextUtil;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author jelli0t
 *
 */
@MappedSuperclass
public abstract class Post {
	
	public static final int ACTIVE = 1;
	public static final int NONACTIVE = 0;
	public static final int MAX_POST_SNIPPET_LENGHT = 384;
	
	public enum Origin {
		ARTICLE(Article.class.getName()),
		PUBLICATION(Publication.class.getName()),
		MEDIA(Media.class.getName());
		
		private String entityName;

		private Origin(String value) {
			this.entityName = value;
		}		
		public String getEntityName() {
			return this.entityName;
		}
		/**/
		public static Origin getByEntityType(Class<? extends Post> entityType) {
			for(Origin origin : Arrays.asList(Origin.values())) {
				if(origin.getEntityName().equals(entityType.getName()))
					return origin;
			}
			return null;
		}
		/**
		 * Varaca ENUM tip na osnovu prosledjenog naziva kalse.
		 * */
		public static Origin getByEntityName(String entityName) {
			for(Origin origin : Arrays.asList(Origin.values())) {
				if(origin.getEntityName().equals(entityName))
					return origin;
			}
			return null;
		}
	}

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
	
	/**
	 * Snippet from post content.<br>
	 * Displays on post preview.
	 * */
	@Column(name="snippet")
	private String snippet;
	
	@Transient
	private Boolean commited;
	@Transient
	private String commitMessage;		
	
	
	public Post() {
		super();
	}

	public Post(int id, String title, String slug, Calendar postDate, String lang, int active, Author author) {
		super();
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

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}	
	
}
