/**
 * 
 */
package org.sobakaisti.security.dao;

import org.sobakaisti.security.model.User;

/**
 * @author Korisnik
 *
 */
public interface UserDao {

	User findUserByUsername(String username);

	long countAllUsers();
}
