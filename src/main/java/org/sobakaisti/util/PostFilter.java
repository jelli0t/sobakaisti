/**
 * 
 */
package org.sobakaisti.util;

import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Tag;

/**
 * @author jelli0t
 * filter za pronalazenje liste postova
 */
public class PostFilter {
	
	private boolean active = true;
	private boolean nonactiveInlude;
	private Author author;
	private Category category;
	private Tag tag;
	private String lang;
		
	/**
	 * Basic constructor
	 * */
	public PostFilter() {
		super();
	}
	
	/**
	 * filter po autoru posta
	 * */
	public PostFilter(boolean active, boolean nonactiveInlude, Author author) {
		super();
		this.active = active;
		this.nonactiveInlude = nonactiveInlude;
		this.author = author;
	}

	/**
	 * kreate filter by post status
	 * */
	public PostFilter(boolean active, boolean nonactiveInlude) {
		super();
		this.active = active;
		this.nonactiveInlude = nonactiveInlude;
	}
	/**
	 * Full constructor
	 * */
	public PostFilter(boolean active, boolean nonactiveInlude, 
						Author author, Category category, 
						Tag tag, String lang) {
		this.active = active;
		this.nonactiveInlude = nonactiveInlude;
		this.author = author;
		this.category = category;
		this.tag = tag;
		this.lang = lang;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isNonactiveInlude() {
		return nonactiveInlude;
	}
	
	public void setNonactiveInlude(boolean nonactiveInlude) {
		this.nonactiveInlude = nonactiveInlude;
	}
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public boolean hasAuthor() {
		return this.author != null ? true : false;
	}
}
