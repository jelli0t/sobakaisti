/**
 * 
 */
package org.sobakaisti.mvt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.sobakaisti.util.TextUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelli0t
 *
 */
@Entity
@Table(name="media")
public class Media extends Post {
	
	public Media() {
		super();
	}	

	public Media(MultipartFile file) {
		super();
		this.file = file;
	}

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
		
		public static boolean contains(String value) {
		    for (MediaType type : MediaType.values()) {
		      if (type.value.equalsIgnoreCase(value))
		        return true;
		    }
		    return false;
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
	
	@Transient
	private MultipartFile file;
	
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
		
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getReadableSize() {
		if(size != 0)
			return TextUtil.convertBytesToReadableSize(this.size);
		else
			return 0 + TextUtil.SPACE_CHAR + TextUtil.MEGABYTE_MEASURE_UNIT;
	}
	
	@Override
	public String getSnippet() {
		if(TextUtil.isEmpty(super.getSnippet()) && TextUtil.notEmpty(this.descriprion)) {
			int endIndex = this.descriprion.length() <= MAX_POST_SNIPPET_LENGHT 
					? this.descriprion.length()-1 : MAX_POST_SNIPPET_LENGHT;
			String shortContent = this.descriprion.substring(0, endIndex);
			return shortContent.replaceAll("<[^>]*>", TextUtil.BLANKO);
		}
		return super.getSnippet();
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
