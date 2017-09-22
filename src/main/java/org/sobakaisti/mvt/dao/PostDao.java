/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;

/**
 * @author jelli0t
 * 
 */
public interface PostDao<T extends Post> {
	
	/**
	 * Pronalazi post po id
	 * @param id
	 * */
	public T find(int id);
	
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

	
}
