/**
 * 
 */
package org.sobakaisti.mvt.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.sobakaisti.util.CalendarUtil;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author jelles
 * Model klasa koja reprezentuje autore (Sobakaiste)
 */
@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Size(min=2, max=20, message="{validation.warn.firstName}")
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Size(min=2, max=30, message="{validation.warn.lastName}")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_BIRTH")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Calendar dob;
	
	@Pattern(regexp="[a-zA-Z\\s\\.\\,]{2,50}", message="{validation.warn.pattern}")
	@Column(name="BIRTHPLACE")
	private String birthplace;
	
	@Pattern(regexp="[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})",
			message="{validation.warn.mailPattern}")
	@Column(name="EMAIL")
	private String email;
	@Column(name="WEBSITE")
	private String website;
	@Column(name="SHORT_BIO")
	private String shortBio;
	
//	@Pattern(regexp="[a-zA-Z\\s\\.\\,\\/]{2,50}", message="{validation.warn.pattern}")
	@Column(name="PROFESSION")
	private String profession;
	@Column(name="AVATAR_PATH")
	private String avatarPath;
	@Column(name="slug")
	private String slug;
			
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	public void setDob(String dob) {
		this.dob = CalendarUtil.getInstance().parseCalendarFromString(dob, CalendarUtil.basicDateFormatter);
	}
	
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getShortBio() {
		return shortBio;
	}
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	/**
	 * Sabira ime i prezima i vraca kao jedan String
	 * @return string
	 * */
	public String getFullName() {
		StringBuilder fullName = new StringBuilder();
		fullName.append(this.firstName != null ? this.firstName : "");
		fullName.append(this.lastName != null ? " "+this.lastName : "");
		return fullName.toString();
	}
	
	@Override
	public String toString() {
		return "[Autor: "+firstName+" "+lastName+", "+birthplace+", "+email+"]";
	}
}
