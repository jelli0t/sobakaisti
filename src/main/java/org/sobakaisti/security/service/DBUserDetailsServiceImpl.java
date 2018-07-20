/**
 * 
 */
package org.sobakaisti.security.service;

import org.sobakaisti.security.dao.UserDao;
import org.sobakaisti.security.model.User;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Korisnik
 * Implementacije servisa za autentifikaciju User-a
 */
@Service("dbUserDetailsService")
public class DBUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//TODO baci exceptione za svaku situaciju
		if(TextUtil.isEmpty(username))
			throw new UsernameNotFoundException("login.exception.emptyCredential");
		
		User user = userDao.findUserByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("login.exception.userNotFound");
		
		return user;
	}

	
	
}
