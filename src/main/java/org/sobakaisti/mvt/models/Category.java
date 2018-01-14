/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jelles
 *
 */
@Entity
@Table(name="category")
public class Category {
	public static final String CATEGORY_ARTS = "arts";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="slug")
	private String slug;

	@Column(name="parent_category_id")
	private int parentId;
	
	@JsonIgnore
	@ManyToMany
	private List<Article> articles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("category: {");
		sb.append(this.id != 0 ? "id : "+this.id : "");
		sb.append(this.name != null ? ", name : "+ this.name : "");		
		return sb.append("}").toString();
	}
	
	@Override
	 public boolean equals(Object obj) {
        if (obj instanceof Category) {
            return id == ((Category)obj).getId();
        }
        return false;
    }
	 
	 
}