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
	/*
	 * Slugs
	 * */
	private String authorSlug;
	private String categorySlug;
	private String tagSlug;
	/*
	 * Limits
	 * */
	private int from;
	private int size;
		
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
	
	/**
	 * Slug related post filter
	 * */	
	public PostFilter(boolean active, String lang, String authorSlug, String categorySlug) {
		super();
		this.active = active;
		this.lang = lang;
		this.authorSlug = authorSlug;
		this.categorySlug = categorySlug;
	}
	
	public PostFilter(boolean active, String lang, String authorSlug, String categorySlug, int from, int size) {
		super();
		this.active = active;
		this.lang = lang;
		this.authorSlug = authorSlug;
		this.categorySlug = categorySlug;
		this.from = from;
		this.size = size;
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
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean hasAuthor() {
		return this.author != null ? true : false;
	}
	
	public String getAuthorSlug() {
		return authorSlug;
	}

	public void setAuthorSlug(String authorSlug) {
		this.authorSlug = authorSlug;
	}

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("filter: {");
		sb.append(isActive() ? "active : 1, " : "");
		sb.append("nonactive_included : " + (isNonactiveInlude() ? "true, " : "false, "));
		sb.append(hasAuthor() ? "author : " + this.author.getFirstName() +", " : "");
		return sb.append("}").toString();
	}
}
