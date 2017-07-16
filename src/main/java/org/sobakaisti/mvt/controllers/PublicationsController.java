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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
		
	@RequestMapping(method=RequestMethod.GET)
	public String showPublicationHomepage(Model model) {		
		List<Publication> publications = publicationService.findAllOrderedPublications();
				
		//TODO napista sve autore koji su relevantni za odabranu kat.
		model.addAttribute("authors", authorDao.getAllAuthors());
//		model.addAttribute("publications", publications);
		
		return "publications";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deletePublicationById(@PathVariable("id") int id){
		boolean isDeleted = publicationService.deletePublicationById(id);
		if(isDeleted)
			return new ResponseEntity<String>("Uspesno obrisano izdanje.", HttpStatus.OK);
		
		return new ResponseEntity<String>("Greska pri brisanju.", HttpStatus.SERVICE_UNAVAILABLE);
	}
			
	
	@RequestMapping(value="/change_status/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> switchPublicationStatus(@PathVariable("id") int id) {	
		String message = publicationService.switchPublicationStatus(id);
		if(message != null){
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}		
		return new ResponseEntity<String>("Greska promeni statusa.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
}
