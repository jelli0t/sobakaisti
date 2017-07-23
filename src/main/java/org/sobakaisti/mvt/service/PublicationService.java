/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelles
 *
 */
public interface PublicationService {

	public static final String ACTIVE_STATUS = "active";
	public static final String NONACTIVE_STATUS = "nonactive";
	
	public List<Publication> findAllOrderedPublications();
	
	/**
	 * Dohvata sva izdanja zadatog autora
	 * @param author
	 * */
	public List<Publication> findAllOrderedPublicationsByAuthor(Author author);
	
	public List<Publication> findAllPublicationsByStatus(String status);
	
	/**
	 * Kreira i uploaduje Publication objekat od prosledjenih parametara
	 * @param title
	 * @param slug
	 * @param content
	 * @param authorId
	 * @param tagIds
	 * @param file
	 * */
	public boolean createAndUploadPublication(String title, String slug, String content, int authorId, int[] tagIds, MultipartFile file);
	
	/**
	 * Brise izdanje za zadati ID
	 * @param int
	 * */
	public boolean deletePublicationById(int id);
	
	/**
	 * Menja status na itemu
	 * TODO nappraviti univerzalnu metodu za sve postove!
	 * @param id
	 * */
	public String switchPublicationStatus(int id);
	
	/**
	 * Prebrojava izdanja na osnovu statusa
	 * */
	public int countPublicationsByStatus(boolean isActive);
	
	/**
	 *  Pronalazi sve autore koji su objavili izdanja
	 * */
	public List<Author> findAllPublicationsAuthors();
	
}
