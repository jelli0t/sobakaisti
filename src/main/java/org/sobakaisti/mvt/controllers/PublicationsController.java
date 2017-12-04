/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.PublicationPostFactory;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.validation.Validation;
import org.sobakaisti.mvt.validation.Validator;
import org.sobakaisti.util.PostRequest;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/publications")
public class PublicationsController {
	private static final Logger logger = LoggerFactory.getLogger(PublicationsController.class);
	
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private Validator validator;
		
	@ModelAttribute
	public void prepare(Model model){
		model.addAttribute("authors", publicationService.findAllPostsAuthors());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showPublicationHomepage(Model model) {		
		model.addAttribute("publications", publicationService.findAllPostOrderedByDate());
		return "publications";
	}
	
	@RequestMapping(value="/by/{author}", method=RequestMethod.GET)
	public String showPublicationsByAuthor(@PathVariable("author") String author, Model model) {
		if(author != null && !author.isEmpty()) {
			Author chosenAuthor = authorDao.findAuthorBySlug(author);
			model.addAttribute("publications", publicationService.findAllOrderedPostsByAuthor(chosenAuthor));
			model.addAttribute("chosenAuthor", chosenAuthor);
			return "publications";
		}else {
			return showPublicationHomepage(model);
		}		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deletePublicationById(@PathVariable("id") int id){
		boolean isDeleted = publicationService.delete(id);
		if(isDeleted)
			return new ResponseEntity<String>("Uspesno obrisano izdanje.", HttpStatus.OK);
		
		return new ResponseEntity<String>("Greska pri brisanju.", HttpStatus.SERVICE_UNAVAILABLE);
	}
			
	
	@RequestMapping(value="/change_status/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> switchPublicationStatus(@PathVariable("id") int id) {	
		String message = publicationService.switchPostStatus(id);
		if(message != null){
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}		
		return new ResponseEntity<String>("Greska promeni statusa.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@RequestMapping(value="/slug/new", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> createNewSlugFromTitle(@RequestBody String title){
		System.out.println("title: "+title);		
		try{
			String slug = StringUtil.makeSlug(title);
			return new ResponseEntity<String>(slug, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Neuspesna konverzija u slug", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadNewPublication(@ModelAttribute(value="postRequest") PostRequest postRequest, 
			RedirectAttributes redirectAttributes) {
		postRequest.setActive(1);		
		Publication uploaded = publicationService.processAndSavePostRequest(postRequest);
		redirectAttributes.addFlashAttribute("uploaded", uploaded);
		return "redirect:/sbk-admin/publication";
	}
}
