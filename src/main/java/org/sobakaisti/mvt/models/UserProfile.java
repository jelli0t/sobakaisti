package org.sobakaisti.mvt.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.sobakaisti.security.model.User;

@Entity
@Getter
@Setter
@Data
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
}
