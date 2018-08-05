package org.sobakaisti.mvt.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author jelli0t
 *
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final boolean COMMENT_DEFAULT_ENABLED = true;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar postDate;	
	
	@Column(name="anonymous_author")
	private String anonymousAuthor;
	
	@Valid
	@Column(name="user_id")
	private User authenticatedAuthor;
	
	@Column(name="enabled", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean enabled;
	
	@Column(name = "post_id")
	private int postId;
	
	@Enumerated(EnumType.STRING)
   	@Column(name = "comment_origin")
	private Post.Origin commentOrigin;
	
	/**
	* Default constructor
	*/
	public Comment() {
		this.enabled = COMMENT_DEFAULT_ENABLED;
		this.postDate = Calendar.getInstance();
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	

	public String getAnonymousAuthor() {
		return anonymousAuthor;
	}

	public void setAnonymousAuthor(String anonymousAuthor) {
		this.anonymousAuthor = anonymousAuthor;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Post.Origin getCommentOrigin() {
		return commentOrigin;
	}

	public void setCommentOrigin(Post.Origin commentOrigin) {
		this.commentOrigin = commentOrigin;
	}

	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}

	public void setAuthenticatedAuthor(User authenticatedAuthor) {
		this.authenticatedAuthor = authenticatedAuthor;
	}

	public User getAuthenticatedAuthor() {
		return authenticatedAuthor;
	}

	public Calendar getPostDate() {
		return postDate;
	}	

	public boolean isAuthenticatedAuthor() {
		return this.authenticatedAuthor != null;
	}
}
