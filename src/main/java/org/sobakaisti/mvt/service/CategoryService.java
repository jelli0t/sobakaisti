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
	/**
	 * nadji sve kategorje po roditelju
	 * */
	public List<Category> findAllSubcategoriesByParent(String parentSlug);
	
	/**
	 * 
	 * */
	public List<Category> findAllSortedSubcategories(String categorySlug, String parentCategorySlug);
}
