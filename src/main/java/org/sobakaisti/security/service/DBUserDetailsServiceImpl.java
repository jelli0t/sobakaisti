/**
 * 
 */
package org.sobakaisti.security.service;

import org.sobakaisti.security.dao.UserDao;
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
		if(TextUtil.isEmpty(username))
			return null;
		
		return userDao.findUserByUsername(username);
	}

}
