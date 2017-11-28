/**
 * 
 */
package org.sobakaisti.mvt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author jelli0t
 *
 */
@Entity
@Table(name="media")
public class Media extends Post {
	
	public enum MediaType {
		PUBLICATION("publication"),
		FEATURED("featured");
		
		private String value;
		
		private MediaType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public static MediaType getMediaType(String value) {
		    for (MediaType type : MediaType.values()) {
		      if (type.value.equalsIgnoreCase(value))
		        return type;
		    }
		    return null;
		}
	}

	@Column(name="file_name")
	private String fileName;
	
	@Column(name="content_type")
	private String contentType;
	
	@Column(name="descriprion")
	private String descriprion;
	
	@Column(name="path")
	private String path;
	
	@Column(name="size")
	private long size;
	
	@Transient
	private boolean posted;
	
	@Transient
	private String uploadResultMessage;
	@Transient
	private MediaType mediaType;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getDescriprion() {
		return descriprion;
	}
	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isPosted() {
		return posted;
	}
	public void setPosted(boolean posted) {
		this.posted = posted;
	}
	public String getUploadResultMessage() {
		return uploadResultMessage;
	}
	public void setUploadResultMessage(String uploadResultMessage) {
		this.uploadResultMessage = uploadResultMessage;
	}	
	
	public MediaType getMediaType() {
		return mediaType;
	}
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("media : {");
		sb.append(this.getId() != 0 ? "id:"+this.getId()+", " : "");
		sb.append(this.getTitle() != null ? "title:"+this.getTitle()+", " : "");
		sb.append(this.getAuthor() != null ? "author:"+this.getAuthor().getFullName()+"" : "");
		sb.append(this.getFileName() != null ? "fullName:"+this.getFileName()+", " : "");
		sb.append("size:"+this.size+", ");
		sb.append(this.contentType != null ? "contentType:"+this.contentType : "");
		return sb.append(" }").toString();
	}
}
