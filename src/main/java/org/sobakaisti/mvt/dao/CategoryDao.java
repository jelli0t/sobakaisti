/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Category;

/**
 * @author jelles
 *
 */
public interface CategoryDao {

	/**
	 * Uzima sve kategorija artikala
	 * */
	public List<Category> findAllCategories();
}
