package org.sobakaisti.mvt.dao.impl;

import java.math.BigInteger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.dao.AccountManagerDao;
import org.sobakaisti.mvt.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountManagerDaotImpl implements AccountManagerDao{
	
	@Autowired
	private SessionFactory sessionFactory;
		
	public AccountManagerDaotImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Account getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void createAccount(Account account) throws HibernateException{
		sessionFactory.getCurrentSession().save(account);		
	}

	@Override
	public void deleteAccount(int id) {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	public boolean checkIfFieldExists(String field, String value){
		Session session = sessionFactory.getCurrentSession();
		BigInteger exist = (BigInteger) session.createSQLQuery("SELECT EXISTS(SELECT 1 FROM account WHERE "+field+"=:value LIMIT 1)").setString("value", value).uniqueResult();
		if(exist.signum() > 0)
			return true;
		else
			return false;
	}

	
}
