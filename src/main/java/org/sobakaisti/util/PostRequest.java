package org.sobakaisti.util;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Post;
import org.springframework.web.multipart.MultipartFile;

public class PostRequest extends Post{

	private String content;
	private int[] categoriesIds;
	private int[] tagIds;
	private MultipartFile media;
	
	/* default constructor */
	public PostRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* MultipartFile instance constructor */
	public PostRequest(MultipartFile media) {
		this.media = media;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int[] getCategoriesIds() {
		return categoriesIds;
	}
	public void setCategoriesIds(int[] categoriesIds) {
		this.categoriesIds = categoriesIds;
	}	

	public int[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(int[] tagIds) {
		this.tagIds = tagIds;
	}

	public MultipartFile getMedia() {
		return media;
	}

	public void setMedia(MultipartFile media) {
		this.media = media;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("post : {");
		sb.append(getId() != 0 ? "id:"+getId()+", " : "");
		sb.append(getTitle() != null ? "title:"+getTitle()+", " : ""); 
		sb.append(getAuthor() != null ? "author:"+getAuthor().getId()+", " : "");
		sb.append(getSlug() != null ? "slug:"+getSlug()+", " : "");
		sb.append(getCategoriesIds() != null ? "categories_size: "+getCategoriesIds().length+", " : "");

		sb.append("active:"+getActive());
		return sb.append('}').toString();
	}
}
