/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Author;

/**
 * @author jelli0t
 *
 */
public interface AuthorService {

	/**
	 * Pronalazi sve autore u sistemu
	 * */
	public List<Author> findAll();
}
