/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/arts")
public class ArtsController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AuthorDao authorDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showArtsRadialMenu(){
		return "mvt_artsMenu";
	}
	
	@RequestMapping(value="/{category}", method=RequestMethod.GET)
	public String showLiteratureHome(Model model, @PathVariable String category){
		model.addAttribute("authors", authorDao.getAllAuthors());
		model.addAttribute("arts", categoryService.findAllSortedSubcategories(category, Category.CATEGORY_ARTS));
		return category;
	}

}
