/**
 * 
 */
package org.sobakaisti.mvt.service.impl;


import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.sobakaisti.mvt.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jelles
 *	implementation of UserDetailsService
 */
@Service("accountDetailsService")
public class AccountDetailsService implements UserDetailsService{

	@Autowired
	private AccountManagerDao accountManagerDao;
	private Account account;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {
	try{
		if(credential.contains("@")){
			System.out.println("Pozivam getAcc..byEmail: "+credential);
			account = accountManagerDao.getAccountByUsernameOrEmail("email", credential);
			System.out.println("posle getAcc..byEmail: "+account);
		}else{
			System.out.println("Pozivam getAcc..byUsername: "+credential);
			account = accountManagerDao.getAccountByUsernameOrEmail("sbk_username", credential);
		}
		
	}catch (Exception e) {
		System.out.println(e.getCause()+" & "+e.getMessage());
	}	
		if(account == null)
			throw new BadCredentialsException("Account doesn't exist in database");
		else
			return account;
	}	
}