/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author jelli0t
 *
 */
@Entity
@Table(name="comment")
public class Comment {
	
	public static final boolean COMMENT_DEFAULT_ENABLED = true;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@NotEmpty(message="{validation.warn.mail.notEmpty}")
	private String content;
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar postDate;
	
	@NotEmpty(message="{validation.warn.mail.notEmpty}")
	private String plainAuthor;
	
	@Valid
	private User authenticatedAuthor;
	
	private boolean enabled;
	
	public Comment(String content, String plainAuthor) {
		super();
		this.content = content;
		this.plainAuthor = plainAuthor;
		this.postDate = Calendar.getInstance();
		this.setEnabled(COMMENT_DEFAULT_ENABLED);
	}	

	public Comment(String content, User authenticatedAuthor) {
		super();
		this.content = content;
		this.authenticatedAuthor = authenticatedAuthor;
		this.postDate = Calendar.getInstance();
		this.setEnabled(COMMENT_DEFAULT_ENABLED);
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getPlainAuthor() {
		return plainAuthor;
	}

	public User getAuthenticatedAuthor() {
		return authenticatedAuthor;
	}

	public Calendar getPostDate() {
		return postDate;
	}	
	
	
}
