/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.IntroPost;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;

/**
 * @author jelli0t
 * 
 */
public interface PostDao<T extends Post, I extends I18nPost> {
	
	/**
	 * Pronalazi post po id
	 * @param id
	 * */
	public T find(int id);
	
	/**
	 * Pronalazi post prema prosledjenom slug-u
	 * @param slug
	 * */
	public T findBySlug(String slug);
	
	/**
	 * Radi save ili update prosledjenog entiteta
	 * @param t
	 * */
	public T save(T t);
	
	/**
	 * Radi save ili update entiteta i iz baze dohvata
	 * upravo sacuvani objekat
	 * @param t
	 * @return dohvata i vraca upravo sacuvani entitet
	 * */
	public T saveOrUpdate(T t);
	
	/**
	 * Radi save ili update entiteta 
	 * koji reprezentuje preveden post.
	 * */
	public I saveOrUpdate(I i);
	
	/**
	 * Pronalazi preveden clanak za Post sa prosledjenim ID.
	 * @param postId
	 * @param fetchingPost 	ako je true dohvata i original post
	 * */
	public I findI18nPostByPostId(int postId, String lang, boolean fetchingPost);
	
	/**
	 *  Brise post sa prosledjenim ID
	 *  @param id
	 * */
	public boolean delete(int id);
	
	/**
	 * Brise prosledjeni objekat iz baze
	 * @param t	objekat za brisanje generickog tipa
	 * */
	public boolean delete(T t);
	
	/**
	 * Pronalazi sve postove koji su u prosledjenom statusu
	 * @param status	1/0
	 * */
	public List<T> findAllPostsByActiveStatus(int status);
	
	/**
	 * broji sve aktivne / neaktivne postove
	 * @param isActive	true/false
	 * */
	public int countPostsByActiveStatus(boolean isActive);

	/**
	 * Kreira pomocni objekat za paginaciju na pregledima postova
	 * @param pagination
	 * @param filter
	 * */
	public Pagination createPostPagination(Pagination pagination, PostFilter filter);

	/**
	 *  pronalaizi listu postova na osnovu parametara paginacije 
	 *  prema prema prosledjenom filteru
	 *  @param pagination
	 *  @param filter
	 * */
	public List<T> findPostsSortedByDate(Pagination pagination, PostFilter filter);
	
	/**
	 * Proverava da li postoje duplikati zadatog slug-a
	 * ako postoje vraca njihov broj
	 * @param slug
	 * */
	public int countSlugDuplicates(String slug);

	/**
	 *  Pronalazi sve autore koji su objavili postove.
	 * */
	public List<Author> findAllPostsAuthors();

	/**
	 *  Menja vrednost na polju active za Post sa prosledjenim ID
	 *  @param id
	 *  @return izmenjenu vrednost na active
	 * */
	public int switchActiveStatus(int id);

	

	public IntroPost getIntroBackgroundPost();
	
	/**
	 * Dohvata prevedeni post na osnovu slua -a
	 * za zadati jezika
	 * */
	public T getTranslatedPostBySlug(String slug, String lang);
	
	/**
	 * Dohvata prevod post sa odredjenim ID na jeziku 
	 * ciji lang code saljemo kao argument
	 * @param id	ID postojeceg posta na default jeziku
	 * @param lang	Lang code: en / es / de...
	 * @return vraca preveden post ili null ukoliko prevod ne postoji
	 * */
	public T getTranslatedPostById(int id, String lang);
	
}
