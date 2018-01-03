package org.sobakaisti.mvt.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article extends Post {	
	
	@Lob
	@Column(name="content")
	private String content;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },targetEntity = Tag.class)
	@JoinTable(name = "article_tag", joinColumns = {
			@JoinColumn(name = "article_id")},
			inverseJoinColumns = { @JoinColumn(name = "tag_id")})
	private List<Tag> tags;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Category.class)
	@JoinTable(name="article_category", 
				joinColumns={@JoinColumn(name="article_id")}, 
				inverseJoinColumns={@JoinColumn(name="category_id")})
	private List<Category> categories;		
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	/* meta podaci za postDate */
//	public CalendarUtil getPostDateMeta() {
//		if(this.postDate != null) {
//			return new CalendarUtil(this.postDate);
//		} else {
//			return new CalendarUtil();
//		}
//	}	
}