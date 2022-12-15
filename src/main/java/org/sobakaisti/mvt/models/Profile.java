package org.sobakaisti.mvt.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@MappedSuperclass
public abstract class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Size(min=2, max=20, message="{validation.warn.firstName}")
	@Column(name="first_name")
	private String firstName;
	
	@Pattern(regexp = "^[\\p{L} .'-]+$", message="{validation.warn.pattern}")
	@Size(min=2, max=30, message="{validation.warn.lastName}")
	@Column(name="last_name")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Calendar dob;
	
	private String profession;
	
	private String location;
	
	private String website;
	
	@Column(name="short_bio")
	private String shortBio;
	
	@Column(name="avatar_name")
	private String avatarName;
	
	private Calendar registered;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=SocialNetwork.class)
	@JoinColumn(name = "profile_id")
	private List<SocialNetwork> socialNetwork;

	public abstract String getEmail();
}
