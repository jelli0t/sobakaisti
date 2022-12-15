package org.sobakaisti.mvt.models;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.sobakaisti.mvt.models.enums.PostOrigin;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

	public static final boolean COMMENT_DEFAULT_ENABLED = true;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="post_date")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar postDate;	
	
	@Column(name="anonymous_author")
	private String anonymousAuthor;
	
	@Valid
	@Column(name="user_id")
	private User authenticatedAuthor;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean enabled;
	
	@Column(name = "post_id")
	private int postId;
	
	@Enumerated(EnumType.STRING)
   	@Column(name = "comment_origin")
	private PostOrigin commentOrigin;

	public boolean isAuthenticatedAuthor() {
		return this.authenticatedAuthor != null;
	}
}
