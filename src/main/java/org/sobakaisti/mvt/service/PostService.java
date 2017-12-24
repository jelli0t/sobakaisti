/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;

/**
 * @author jelli0t
 *
 */
public interface PostService<T extends Post> {
	
	public static final String ACTIVE_STATUS = "active";
	public static final String NONACTIVE_STATUS = "nonactive";
	/* Full class names */
	public static final String ARTICLE_CLASS_NAME = Article.class.getName();
	public static final String PUBLICATION_CLASS_NAME = Publication.class.getName();
	public static final String MEDIA_CLASS_NAME = Media.class.getName();
	
	
	public T findById(int id);
	
	/**
	 * Pronadji apsolutno sve objekte jednog entiteta
	 * */
	public List<T> findAll();
	
	/**
	 * Pronalazi sve objave sortirane po datumu objavljivnja
	 * Metoda kreira podrazumevani PostFilter koji filtrira samo 
	 * aktivne objave. 
	 * */
	public List<T> findAllPostOrderedByDate();
	
	/**
	 * Pronalazi sve objave od zadatog autora, sortirane po datumu objavljivnja
	 * Metoda kreira PostFilter sa autorom kao parametrom i statusom active = 1
	 * @param author
	 * */
	public List<T> findAllOrderedPostsByAuthor(Author author);
	
	/**
	 * Pronalazi sve objave u  active statusu kog prosledjujemo kao String
	 * @param status
	 * */
	public List<T> findAllPostsByStatus(String status);
	
	/**
	 * Brise Post objekat sa zadatim ID.
	 * @param id
	 * */
	public boolean delete(int id);
	
	/**
	 * Broji sve objave na osnovu statusa
	 * @param isActive
	 * */
	public int countPostsByStatus(boolean isActive);

	/**
	 * Menja vrednost active polja na Post derivatima.
	 * @param id
	 * */
	public String switchPostStatus(int id);

	/**
	 * Pronalazi sve autore koje su objavili neki Post
	 * */
	public List<Author> findAllPostsAuthors();
	
	/**
	 * Dohvata potpunu listu tagova za prosledjeni entitet
	 * koji nasledjuje Post
	 * @param	post subclass
	 * */
	public List<Tag> fatchPostFullTagList(T t);

	/**
	 * Dodaje sufiks na proslednjeni slug ukoliko isti vec postoji.
	 * Sufiks predstavlja broj ponavljanja slug-a u tabeli
	 * @param slug
	 * */
	public String addSuffixIfDuplicateExist(String slug);
	
	/**
	 * Procesuira PostRequest objekat sa submitovane forme i
	 * konvertuje u odgovarajucu podklasu Post-a a zatim cuva
	 * tu objavu.
	 * @param postRequest
	 * */
	public T processAndSavePostRequest(PostRequest postRequest);
	
	/**
	* Procesuira ModelAttribute Post objekat i cuva ga u bazi.
	* @param post
	* */
	public T processAndSaveSubmittedPost(T post);
}
