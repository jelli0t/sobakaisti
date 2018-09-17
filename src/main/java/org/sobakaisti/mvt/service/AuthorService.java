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
public interface AuthorService extends ProfileServiceImpl<AuthorProfile> {

	/**
	 * Pronalazi sve autore u sistemu
	 * */
	public List<Author> findAll();
	
	/**
	 * Dohvata autora na osnovu prosledjenog slug-a
	 * 
	 * @param slug
	 * */
	public Author findBySlug(String slug);
	
	/***/
	public Author findFull(String slug);
	
	/**
	 * Dohvata listu citata iz manifesta
	 * */
	public List<String> getAuthorsManifestQuotes();
}
