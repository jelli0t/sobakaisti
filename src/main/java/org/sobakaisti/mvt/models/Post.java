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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@MappedSuperclass
public abstract class Post {
	
	public static final int ACTIVE = 1;
	public static final int NONACTIVE = 0;
	public static final int MAX_POST_SNIPPET_LENGHT = 384;

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

}
