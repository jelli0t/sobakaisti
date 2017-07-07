/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * @author jelles
 *
 */
@Entity
@Table(name="publication")
public class Publication {
	
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
	
	@Column(name="path")
	private String path;
		
	@Column(name="post_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar postDate;
	
	@Column(name="active")
	private byte active;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Author author;
	
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public Calendar getPostDate() {
		return postDate;
	}
	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
}