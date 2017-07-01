/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.mvt.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/publications")
public class PublicationsController {
	
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublicationService publicationService;
	
	@Autowired
	private CategoryService categoryService;
	
		
	@RequestMapping(method=RequestMethod.GET)
	public String showPublicationHomepage(Model model) {		
		List<Publication> publications = publicationService.findAllOrderedPublications();
		
		//TODO napista sve autore koji su relevantni za odabranu kat.
		model.addAttribute("authors", authorDao.getAllAuthors());
		model.addAttribute("publications", publications);
		
		return "publications";
	}
			
			
}
