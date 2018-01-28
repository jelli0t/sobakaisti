/**
 * 
 */
package org.sobakaisti.mvt.i18n.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sobakaisti.mvt.models.Media;

/**
 * @author Korisnik
 *
 */
@Entity
@Table(name="i18n_media")
public class I18nMedia extends I18nPost {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =
		{
		        CascadeType.DETACH,
		        CascadeType.MERGE,
		        CascadeType.REFRESH,
		        CascadeType.PERSIST
		},
		targetEntity = Media.class)
		@JoinColumn(name="media_id")
		private Media media;

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
}