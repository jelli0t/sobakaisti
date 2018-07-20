package org.sobakaisti.security.service;

import org.sobakaisti.security.model.User;

public interface UserService {
	
	long countAllUsers();
	
	boolean haveUsersAtAll();

	boolean checkIfUserExists(String username, String email);

	User initialAdministratorSignup(User admin);

	User signupNewUser(User user);

}
