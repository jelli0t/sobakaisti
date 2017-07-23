/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;
import java.util.Set;

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
	 * pronalazi autora po polju slug
	 * @param String slug
	 * @return Author
	 * */
	public Author findAuthorBySlug(String slug);
	
	/**
	 * 	Vraca listu svih sobakaista
	 * @return
	 * */
	public List<Author> getAllAuthors();
	
	public void persistAuthor(Author author);
	
	public void deleteAuthor(int id);
	
	
	
}
