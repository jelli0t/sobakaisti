package org.sobakaisti.mvt.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class AuthorProfile extends Profile {

	@OneToOne(targetEntity = Author.class)
	@JoinColumn(name = "author_id")
    @MapsId
	private Author author;
	
	public AuthorProfile() {
		super();
	}
	public AuthorProfile(Author author) {
		super();
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}	
	
	@Override
	public String getFirstName() {
		return this.author.getFirstName();
	}
	
	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}
	
	@Override
	public String getLastName() {
		return this.author.getLastName();
	}	
	@Override
	public String getEmail() {
		return this.author.getEmail() != null ? this.author.getEmail() : null;
	}
	
	/**
	 * Sabira ime i prezima i vraca kao jedan String
	 * @return string
	 * */
	public String getFullName() {
		StringBuilder fullName = new StringBuilder();
		fullName.append(getFirstName() != null ? getFirstName() : "");
		fullName.append(getLastName() != null ? " "+getLastName() : "");
		return fullName.toString();
	} 
}