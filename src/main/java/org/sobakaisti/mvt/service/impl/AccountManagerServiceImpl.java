/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import org.hibernate.HibernateException;
import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.sobakaisti.mvt.models.Account;
import org.sobakaisti.mvt.service.AccountManagerService;
import org.sobakaisti.mvt.service.CurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *
 */
@Service
public class AccountManagerServiceImpl extends CurrentTime implements AccountManagerService {

	@Autowired
	private AccountManagerDao accountManagerDao;
	
	@Override
	public void createAccount(Account account) {
		 account.setDateOfRegistration(getCurrentDate());
		 account.setRole("USER");
		 try{
			 accountManagerDao.createAccount(account);
		 }catch (HibernateException he) {
			System.out.println("Uhvacen Hibernate exeption: "+he.getMessage());
		}
		 
	}

}
