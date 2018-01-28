/**
 * 
 */
package org.sobakaisti.mvt.i18n.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.sobakaisti.mvt.models.Post;

/**
 * @author Korisnik
 *
 */
@MappedSuperclass
public abstract class I18nPost {

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
	
	@Column(name="lang")
	private String lang;	
	
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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
