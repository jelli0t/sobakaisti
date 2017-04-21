/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Category;

/**
 * @author jelles
 * Kalsa za pristup podacima o kategorijama clanaka
 */
public interface CategoryDao {

	/**
	 * Uzima sve kategorija artikala
	 * */
	public List<Category> findAllCategories();
	
	public Category getCategoryById(int id);
	
	/**
	 * Metoda koja dohvate kategorije clanaka za navedene id-ijeve
	 * @param niz id-eva koje trazimo
	 * @return lista kategorija
	 * */
	public List<Category> getAllCategoriesByIds(List<Integer> ids);
}