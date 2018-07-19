package org.sobakaisti.security.service;

public interface UserService {
	
	long countAllUsers();
	
	boolean haveUsersAtAll();

	boolean checkIfUserExists(String username, String email);

}
