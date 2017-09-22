/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;

/**
 * @author NEKS Office
 *
 */
public interface PublicationDao {

	public List<Publication> findAllOrderedPublications();

	/**
	 * Pronalazi sva izdanaj po Autoru
	 * @param author
	 * */
	public List<Publication> findAllPublicationByAuthor(Author author);
	
	/**
	 * Cuva Publication objekat kao ekvivalent uploadovanom fiajlu
	 * @param publication
	 * */
	public boolean savePublication(Publication publication);
	
	/**
	 * Brise izdanje na osnovu ID-a
	 * @param int
	 * */
	public boolean deletePublicatinById(int id);

	public int switchPublicationStatus(int id);
	
	/**
	 * Probroj izdanja prema statusu
	 * @param boolean
	 * TODO podigni metodu u superklasu
	 * */
	public int countPublicationsByStatus(boolean isActive);

	/**
	 * Dohvata sve publikacije sa zadatim statusom
	 * @param int status
	 * */
	public List<Publication> findAllPublicationsByStatus(int status);
	
	/**
	 * Broji broj ponavljanja zadatog slug-a
	 * @param slug
	 * TODO napravi univerzalnu metodu
	 * */
	public int countSlugDuplicates(String slug);
	
	/**
	 *  Pronalazi sve autore koji su objavili izdanja
	 * */
	public List<Author> findAllPublicationsAuthors();

	/**
	 * TODO podici u nadklasu!
	 * Kreira pomocni objekat za paginaciju pregleda postova
	 * @param
	 * */
	Pagination createPostPagination(Pagination pagination, PostFilter filter);
}
