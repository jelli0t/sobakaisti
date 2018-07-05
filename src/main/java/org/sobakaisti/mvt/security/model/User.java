package org.sobakaisti.mvt.security.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.sobakaisti.mvt.security.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Reprezentuje user account entitet sa kojim se korisnik loguje na sistem.
 */
@Entity
@Table(name="user")
public class User implements UserDetails {

	private int id;
	private String username;
	private String password;
	private Authority.Role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Authority.Role getRole() {
		return role;
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
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
