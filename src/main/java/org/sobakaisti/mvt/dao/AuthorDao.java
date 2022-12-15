package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;

public interface AuthorDao {

	Author getAuthorById(Long id);
	
	Author findAuthorBySlug(String slug);
	
	/**
	 * Dohvata potpune informacije o autoru, ukljucujuci i profil.
	 * Autora trazi po ID
	 * */
	Author findFull(int id);
	/**
	 * Dohvata potpune informacije o autoru, ukljucujuci i profil.
	 * Autora trazi po njegovom slugu
	 * */
	public Author findFull(String slug);
	
	List<Author> getAllAuthors();
		
	void persistAuthor(Author author);
	
	public boolean saveOrUpdate(Author author);
	
	public void deleteAuthor(int id);

}
