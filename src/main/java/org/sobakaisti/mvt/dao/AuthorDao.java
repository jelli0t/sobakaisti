/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;

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
	 * Dohvata potpune informacije o autoru, ukljucujuci i profil.
	 * Autora trazi po ID
	 * */
	public Author findFull(int id);
	/**
	 * Dohvata potpune informacije o autoru, ukljucujuci i profil.
	 * Autora trazi po njegovom slugu
	 * */
	public Author findFull(String slug);
	
	/**
	 * 	Vraca listu svih sobakaista
	 * @return
	 * */
	public List<Author> getAllAuthors();
		
	public void persistAuthor(Author author);
	
	public void deleteAuthor(int id);

}
