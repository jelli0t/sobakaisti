package org.sobakaisti.util;

import java.util.List;

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Media.MediaType;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Tag;
import org.springframework.web.multipart.MultipartFile;

public class PostRequest extends Post{

	private String content;
	private int[] categoriesIds;
	private int[] tagIds;
	
	private List<Tag> tags;
	/**
	 * Id uploadovanog publiction media
	 * */
	private int publicationMediaId;
	/**
	 * Id uploadovanog featured media
	 * */
	private int featuredImageId;
	/**
	 * Upload-ovana datoteka
	 * */
	private MultipartFile media;
	private MediaType mediaType;
	
	/* default constructor */
	public PostRequest() {}
	
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

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public int getPublicationMediaId() {
		return publicationMediaId;
	}

	public void setPublicationMediaId(int publicationMediaId) {
		this.publicationMediaId = publicationMediaId;
	}

	public int getFeaturedImageId() {
		return featuredImageId;
	}

	public void setFeaturedImageId(int featuredImageId) {
		this.featuredImageId = featuredImageId;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("post : {");
		sb.append(getId() != 0 ? "id:"+getId()+", " : "");
		sb.append(getTitle() != null ? "title:"+getTitle()+", " : ""); 
		sb.append(getAuthor() != null ? "author:"+getAuthor().getId()+", " : "");
		sb.append(getSlug() != null ? "slug:"+getSlug()+", " : "");
		sb.append(getCategoriesIds() != null ? "categories_size: "+getCategoriesIds().length+", " : "");
		sb.append("publicationMediaId:"+publicationMediaId+", ");
		sb.append("featuredImageId:"+featuredImageId+", ");
		sb.append("active:"+getActive());
		sb.append(getTags() != null ? "tags: "+getTags().size() : "");
		return sb.append('}').toString();
	}

}
