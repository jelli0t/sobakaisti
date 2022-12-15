/**
 * 
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.sobakaisti.mvt.i18n.model.I18nPublication;
import org.sobakaisti.util.TextUtil;

@Entity
@Table(name="publication")
public class Publication extends Post {	
	
	@Lob
	@Column(name="content")
	private String content;
	
	@Column(name="path")
	private String path;
	
	@Column(name="downloaded")
	private int downloaded;
	
	@ManyToMany(cascade =
        {
	        CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST
        }, fetch=FetchType.EAGER)	
	@JoinTable(name = "publication_tag", joinColumns = {
			@JoinColumn(name = "publication_id")},
			inverseJoinColumns = { @JoinColumn(name = "tag_id")})
	private List<Tag> tags;
	
	@ManyToOne(
			fetch = FetchType.EAGER,
			cascade = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
			},
			targetEntity = Media.class
	)
	@JoinColumn(name="media_id")
	private Media media;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Media.class)
	@JoinColumn(name="featured_img_id")
	private Media featuredImage;
	
	/* Default constructor */
	public Publication() {
		super();
	}
	
	/* Construct translated Publication*/	
	public Publication(Publication p, I18nPublication ip) {
		super(p.getId(), ip.getTitle(), p.getSlug(), p.getPostDate(), ip.getLang(), p.getActive(), p.getAuthor());
		this.content = ip.getContent();
		this.path = p.getPath();
		this.downloaded = p.getDownloaded();
		this.tags = p.getTags();
		this.media = p.getMedia();
		this.featuredImage = p.getFeaturedImage();
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
		
	public int getDownloaded() {
		return downloaded;
	}
	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
		
	public Media getMedia() {
		return media;
	}
	
	public void setMedia(Media media) {
		this.media = media;
	}
	
	public Media getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(Media featuredImage) {
		this.featuredImage = featuredImage;
	}
	

	@Override
	public String getSnippet() {
		if(TextUtil.isEmpty(super.getSnippet()) && TextUtil.notEmpty(this.content)) {
			int endIndex = this.content.length() <= MAX_POST_SNIPPET_LENGHT 
					? this.content.length()-1 : MAX_POST_SNIPPET_LENGHT;
			String shortContent = this.content.substring(0, endIndex);
			return shortContent.replaceAll("<[^>]*>", TextUtil.BLANKO);
		}
		return super.getSnippet();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("publication : {");
		sb.append(this.getId() != 0 ? "id:"+this.getId()+", " : "");
		sb.append(this.getTitle() != null ? "title:"+this.getTitle()+", " : "");
		sb.append(this.getAuthor() != null ? "author:"+this.getAuthor().getFullName()+"" : "");
		sb.append(this.getPostDate() != null ? "postDate : "+getPostDate().getTime()+", " : "");
		sb.append(getTags() != null ? "tags: "+getTags().size() : "");
		return sb.append('}').toString();
	}
	
}