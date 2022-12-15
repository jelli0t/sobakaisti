package org.sobakaisti.mvt.models;

import java.util.Calendar;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import org.sobakaisti.util.CalendarUtil;
import org.sobakaisti.util.StringUtil;
import org.sobakaisti.util.TextUtil;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=2, max=20, message="{validation.warn.firstName}")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Size(min=2, max=30, message="{validation.warn.lastName}")
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_BIRTH")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Calendar dob;
	
	@Pattern(regexp="^[\\p{L} .,'-]+$", message="{validation.warn.pattern}")
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
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="author", targetEntity=AuthorProfile.class)
	private Profile profile;
			
	public void setDob(String dob) {
		this.dob = CalendarUtil.getInstance()
				.parseCalendarFromString(dob, CalendarUtil.basicDateFormatter);
	}

	public void setSlug(String slug) {
		if(TextUtil.isEmpty(slug)) {
			this.slug = StringUtil.makeSlug(this.getFullName());
		}
		this.slug = slug;
	}
	
	public String getFullName() {
		return Stream.of(getFirstName(), getLastName())
				.filter(Predicate.not(String::isEmpty))
				.collect(Collectors.joining(" "));
	}

	@Override
	public String toString() {
		return "[Autor: "+firstName+" "+lastName+", "+birthplace+", "+email+"]";
	}
}
