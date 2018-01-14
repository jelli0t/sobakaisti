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
	 * Dohvata podrazumevanu kategoriju. Tj kategoriju roditelja
	 * gde se po defaultu smestaju postovi bes selektovane konkretne 
	 * kategorije. Default kategorija je "Radovi" (arts)
	 * */
	public Category getDefaultCategory();
	
	/**
	 * Metoda koja dohvate kategorije clanaka za navedene id-ijeve
	 * @param niz id-eva koje trazimo
	 * @return lista kategorija
	 * */
	public List<Category> getAllCategoriesByIds(List<Integer> ids);
	
	/**
	 * 	Vraca listu pod-kategorija za prosledjen slug roditelja
	 * */
	public List<Category> findAllSubcategoriesByParent(String parentSlug);
	
	/**
	 * pronalazi kategoriju po njenom slug-u
	 * @param String
	 * */
	public Category findCategoryBySlug(String slug);

	List<Category> findListOfCategoriesByIds(List<Integer> ids);
}
