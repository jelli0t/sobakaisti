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
	private MultipartFile publication;
	private MultipartFile featuredImg;
	
	private List<Category> categories = new ArrayList<>();
	private Category[] categoryArray = null;
		
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
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	

	public Category[] getCategoryArray() {
		return categoryArray;
	}

	public void setCategoryArray(Category[] categoryArray) {
		this.categoryArray = categoryArray;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("post : {");
		sb.append(getId() != 0 ? "id:"+getId()+", " : "");
		sb.append(getTitle() != null ? "title:"+getTitle()+", " : ""); 
		sb.append(getAuthor() != null ? "author:"+getAuthor().getId()+", " : "");
		sb.append(getSlug() != null ? "slug:"+getSlug()+", " : "");
		sb.append(getCategoriesIds() != null ? "categories_size: "+getCategoriesIds().length+", " : "");
		sb.append(categories != null ? "categories_list_size: "+categories.size()+", " : "");
		sb.append(categoryArray != null ? "categories_array_size: "+categoryArray.length+", " : "");

		sb.append("active:"+getActive());
		return sb.append('}').toString();
	}
}
