package org.sobakaisti.mvt.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.sobakaisti.security.model.User;

@Entity
@Table(name = "profile")
public class UserProfile extends Profile {
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
    @MapsId
	private User user;

	@Override
	public String getEmail() {
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}
