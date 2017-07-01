/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Category;

/**
 * @author jelles
 *
 */
public interface CategoryService {
	
	public static final String PUBLICATIONS_SLUG = "publications";
	/**
	 * nadji sve kategorje po roditelju
	 * */
	public List<Category> findAllSubcategoriesByParent(String parentSlug);
	
	/**
	 * 
	 * */
	public List<Category> findAllSortedSubcategories(String categorySlug, String parentCategorySlug);

	/**
	 * Pronalazi kategoriju po njenom slug-u
	 * @param String
	 * */
	public Category findCategoryBySlug(String slug);
}
