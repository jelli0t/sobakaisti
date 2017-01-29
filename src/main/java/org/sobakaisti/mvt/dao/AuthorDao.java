/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Author;

/**
 * @author jelles
 *
 */
public interface AuthorDao {
	/**
	 * Vraca autora na osnovu njegovog ID-a
	 * @param id - integer
	 * */
	public Author getAuthorById(int id);
	/**
	 * 	Vraća listu svih autora (sobakaista)
	 * */
	public List<Author> getAllAuthors();
	
	public void persistAuthor(Author author);
	
	public void deleteAuthor(int id);
}
