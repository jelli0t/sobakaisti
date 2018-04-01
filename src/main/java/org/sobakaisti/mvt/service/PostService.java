/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.sobakaisti.util.PostRequest;
import org.springframework.ui.Model;

/**
 * @author jelli0t
 *
 */
public interface PostService<T extends Post> {
	
	public static final int INIT_POST_BUNDLE_SIZE = 10;
	public static final int LOADED_POST_BUNDLE_SIZE = 6;
	
	public static final String POST_SORTING_ALLOWED_PARAM = "postSortingAllowed";
	
	public static final String ACTIVE_STATUS = "active";
	public static final String NONACTIVE_STATUS = "nonactive";
	/* Full class names */
	public static final String ARTICLE_CLASS_NAME = Article.class.getName();
	public static final String PUBLICATION_CLASS_NAME = Publication.class.getName();
	public static final String MEDIA_CLASS_NAME = Media.class.getName();
	
	
	public T findById(int id);
	
	/**
	 * Pronalazi post prema prosledjenom slug-u.
	 * Uzima uu obzir jezik iz locale-a i na osnovu toga
	 * dohvata post na defaultnom jeziku ili preveden
	 * @param slug
	 * */
	public T findBySlug(String slug);
	
	/**
	 * Pronadji apsolutno sve objekte jednog entiteta
	 * */
	public List<T> findAll();
	
	/**
	 * Dohvata listu postova na osnovu parametara iz filtera 
	 * @param postFilter
	 * */
	List<T> find(PostFilter postFilter);
	
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
	 * Metoda izvrsava brisanje Posta i vraca rezultat operacije
	 * @param id	Post ID
	 * @return {@link CommitResult}
	 * */
	public CommitResult commitDelete(int id);
	
	/**
	 * Broji sve objave na osnovu statusa
	 * @param isActive
	 * */
	public int countPostsByStatus(boolean isActive);

	/**
	 * Menja vrednost active polja na Post derivatima.
	 * @param id
	 * */
	public CommitResult switchPostStatus(int id);

	/**
	 * Pronalazi sve autore koje su objavili neki Post
	 * */
	public List<Author> findAllPostsAuthors();
	
	List<Author> findAllPostsAuthorsInCategory(String categorySlug);
	
	/**
	 * Dohvata potpunu listu tagova za prosledjeni entitet
	 * koji nasledjuje Post
	 * @param	post subclass
	 * */
	public List<Tag> fatchPostFullTagList(T t);
	
	/**
	 * Dohvata sve definisane kategorije
	 * @return lista kategorija
	 * */
	public List<Category> findAllCategories();

	/**
	 * Dodaje sufiks na proslednjeni slug ukoliko isti vec postoji.
	 * Sufiks predstavlja broj ponavljanja slug-a u tabeli
	 * @param slug
	 * */
	public String addSuffixIfDuplicateExist(String slug);
	
	
	/**
	* Procesuira ModelAttribute Post objekat i cuva ga u bazi.
	* @param post
	* */
	public T processAndSaveSubmittedPost(T post);
	
	
//	public I18nPost saveTranslatedPost(I18nPost i18nPost);

	/**
	 * Na osnovu prosledjenog coda dohvata odgovarajucu poruku iz property fajla
	 * */
	String getMessage(String code);
	
	/**
	 * Metoda generise listu model atributa za prikaz liste postova. Na osnovu prosledjenih
	 * parametara generise objekat za paginaciju i filter za dohvatanje postova.
	 * @param pagination
	 * @param status		status postova
	 * @param				autorov slug
	 * */
	Model generateModelAttributesForPostsListing(Pagination pagination,	String status, String authorSlug);

	/**
	 * Dohvata podrazumevanu kategoriju
	 * */
	Category getDefaultCategory();

	/**
	 * iz LocaleContexHolder-a dohvata locale i uzima
	 * language kod i vraca ga kao string.
	 * */
	String getPostLanguage();

	

	
}
