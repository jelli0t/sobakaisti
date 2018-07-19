package org.sobakaisti.security.service;

import org.sobakaisti.security.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
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
}
