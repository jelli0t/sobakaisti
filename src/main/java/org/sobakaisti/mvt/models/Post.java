/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Calendar;

/**
 * @author jelli0t
 *
 */
public abstract class Post {
	
	public static final int ACTIVE = 1;
	public static final int NONACTIVE = 0;

	protected long id;
	protected String title;	
	protected String slug;
	protected Calendar postDate;
	protected int active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
}
