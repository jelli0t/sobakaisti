package org.sobakaisti.mvt.models;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="account")
public class Account implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="acc_id")
	private int accId;
	@Column(name="sbk_username")
	private String username;
	@Column(name="sbk_password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="first_name")
	@ColumnDefault("")
	private String firstName;
	@Column(name="last_name")
	@ColumnDefault("")
	private String lastName;
	@Column(name="avatar_uri")
	@ColumnDefault("")
	private String avatarUri;
	@Column(name="role")
	@ColumnDefault(value="USER")
	private String role;
	@Column(name="date_of_reg", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfRegistration;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> role = new HashSet<SimpleGrantedAuthority>(1);
		role.add(new SimpleGrantedAuthority(getRole()));		
		return role;
	}
//	get ROLE from DB for GrantedAuthority
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role = role.toUpperCase();
	}	
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAvatarUri() {
		return avatarUri;
	}
	public void setAvatarUri(String avatarUri) {
		this.avatarUri = avatarUri;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}