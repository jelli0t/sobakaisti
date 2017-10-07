package org.sobakaisti.util;

import org.sobakaisti.mvt.models.Post;
import org.springframework.web.multipart.MultipartFile;

public class PostRequest extends Post{

	private String content;
	private int[] categories;
	private int[] tags;
	private MultipartFile publication;
	private MultipartFile featuredImg;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int[] getCategories() {
		return categories;
	}

	public void setCategories(int[] categories) {
		this.categories = categories;
	}

	public int[] getTags() {
		return tags;
	}

	public void setTags(int[] tags) {
		this.tags = tags;
	}

	public MultipartFile getPublication() {
		return publication;
	}

	public void setPublication(MultipartFile publication) {
		this.publication = publication;
	}

	public MultipartFile getFeaturedImg() {
		return featuredImg;
	}

	public void setFeaturedImg(MultipartFile featuredImg) {
		this.featuredImg = featuredImg;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("post : {");
		sb.append(getId() != 0 ? "id:"+getId()+", " : "");
		sb.append(getTitle() != null ? "title:"+getTitle()+", " : ""); 
		sb.append(getSlug() != null ? "slug:"+getSlug()+", " : "");
		sb.append("active:"+getActive());
		return sb.append('}').toString();
	}
}
