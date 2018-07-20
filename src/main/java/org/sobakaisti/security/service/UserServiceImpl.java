package org.sobakaisti.security.service;

import org.sobakaisti.security.Authority.Role;
import org.sobakaisti.security.dao.UserDao;
import org.sobakaisti.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public long countAllUsers() {
		return userDao.countAllUsers();
	}

	@Override
	public boolean haveUsersAtAll() {		
		return countAllUsers() > 0;
	}
	
	@Override
	public boolean checkIfUserExists(String username, String email) {
		return userDao.checkIfUserExists(username, email);
	}
	
	@Override
	public User initialAdministratorSignup(User admin) {
		boolean enableInitAdminSignup = !this.haveUsersAtAll();				
		if(enableInitAdminSignup)
			admin.setRole(Role.ROLE_ADMIN);				
		return this.signupNewUser(admin);
	}
	
	@Override
	public User signupNewUser(User user) {
		User persistedUser = null;
		if(user != null) {
			final String encodedPasswd = passwordEncoder.encode(user.getPassword()); 
			user.setPassword(encodedPasswd);
			user.setEnabled(true);
			user.setLocked(false);
			/* Resetuj rolu na basic */
			if(user.getRole() == null || !user.getRole().equals(Role.ROLE_ADMIN))
				user.setRole(Role.ROLE_USER);
			/* save */
			persistedUser = userDao.saveUser(user);
		}
		return persistedUser;
	}
}
