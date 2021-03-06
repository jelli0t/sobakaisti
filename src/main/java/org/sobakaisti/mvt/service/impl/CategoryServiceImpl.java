/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> findAllSubcategoriesByParent(String parentSlug) {
		return categoryDao.findAllSubcategoriesByParent(parentSlug);
	}

	@Override
	public List<Category> findAllSortedSubcategories(String categorySlug, String parentCategorySlug) {
		List<String> slugs = categoryDao.fetchArtsSlugsSortedBySelectedArt(categorySlug);
		for(String slug : slugs)
			System.out.println(slug);
		
		List<Category> subcategories = this.findAllSubcategoriesByParent(parentCategorySlug);		
		if(subcategories != null){
			List<Category> sortedCategories = new ArrayList<Category>(subcategories.size());
			for(Category category : subcategories){
				/* odabranu kat. postavi kao prvu na sortiranoj listi */
				if(category.getSlug().equals(categorySlug)){
					sortedCategories.add(0, category);
					subcategories.remove(category);
					break;
				}
			}
			/* add rest of categories */
			sortedCategories.addAll(subcategories);
			return sortedCategories;
		}
		return null;
	}
	
	@Override
	public List<String> findArtsSlugsSortedBySelectedArt(String categorySlug) {
		return categoryDao.fetchArtsSlugsSortedBySelectedArt(categorySlug);
	}

	@Override
	public Category findCategoryBySlug(String slug) {
		if(slug != null || !slug.equals("")){
			return categoryDao.findCategoryBySlug(slug);
		}
		return null;
	}

	@Override
	public List<Category> findListOfCategoriesByIdsArray(int[] ids) {
		List<Integer> categoriesIds = new ArrayList<Integer>(ids.length);
		for(int i=0; i < ids.length; i++) {
			categoriesIds.add(ids[i]);
		}
		return categoriesIds.size() > 0 ? categoryDao.findListOfCategoriesByIds(categoriesIds) : null;
	}
}
