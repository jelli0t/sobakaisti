/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *	implementation of UserDetailsService
 */
@Service("accountDetailsService")
public class AccountDetailsService implements UserDetailsService{

	@Autowired
	private AccountManagerDao accountManagerDaotImpl;
	@Override
	public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {
		if(credential.contains("@")){
			return accountManagerDaotImpl.getAccountByUsernameOrEmail("email", credential);
		}else{
			return accountManagerDaotImpl.getAccountByUsernameOrEmail("sbk_username", credential);
		}
	}
	
}
