package org.sobakaisti.security.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.sobakaisti.security.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Reprezentuje user account entitet sa kojim se korisnik loguje na sistem.
 */
@Entity
@Table(name="user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	@NotEmpty(message="{validation.warn.notNull}")
	private String username;
	@NotEmpty(message="{validation.warn.notNull}")
	private String password;
	
	@Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
		message="{validation.warn.mailPattern}")
	@Column(name="email")
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Authority.Role role;
	
	@Column(name="enabled", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean enabled;
	
	@Column(name="locked", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean locked;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public Authority.Role getRole() {
		return role;
	}	
	
	public void setRole(Authority.Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@SuppressWarnings("serial")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authorities = null;
		if(this.role != null) {
			authorities = new HashSet<Authority>() {
				{
					add(new Authority(role));
				}
			};
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	

}
