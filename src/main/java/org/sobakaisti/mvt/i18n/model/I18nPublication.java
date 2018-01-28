package org.sobakaisti.mvt.i18n.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sobakaisti.mvt.models.Publication;

@Entity
@Table(name="i18n_publication")
public class I18nPublication extends I18nPost {
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =
	{
	        CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST
	},
	targetEntity = Publication.class)
	@JoinColumn(name="publication_id")
	private Publication publication;

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

}
