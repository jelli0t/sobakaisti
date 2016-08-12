package org.sobakaisti.mvt.dao;

import org.sobakaisti.mvt.models.Account;

public interface AccountManagerDao {
	public Account getAllAccounts();
	public void createAccount(Account account);
	public void deleteAccount(int id);
	public boolean checkIfFieldExists(String value, String field);
}
