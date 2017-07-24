/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Category;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.CategoryService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.validation.Validation;
import org.sobakaisti.mvt.validation.Validator;
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
	private Validator validator;
		
	@ModelAttribute
	public void prepare(Model model){
		model.addAttribute("authors", publicationService.findAllPublicationsAuthors());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showPublicationHomepage(Model model) {		
		model.addAttribute("publications", publicationService.findAllOrderedPublications());
		return "publications";
	}
	
	@RequestMapping(value="/by/{author}", method=RequestMethod.GET)
	public String showPublicationsByAuthor(@PathVariable("author") String author, Model model) {
		if(author != null && !author.isEmpty()) {
			Author chosenAuthor = authorDao.findAuthorBySlug(author);
			model.addAttribute("publications", publicationService.findAllOrderedPublicationsByAuthor(chosenAuthor));
			model.addAttribute("chosenAuthor", chosenAuthor);
			return "publications";
		}else {
			return showPublicationHomepage(model);
		}		
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
	
	@RequestMapping(value="/slug/new", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> createNewSlugFromTitle(@RequestBody String title){
		System.out.println("title: "+title);		
		try{
			String slug = StringUtil.makeSlugFromTitle(title);
			return new ResponseEntity<String>(slug, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Neuspesna konverzija u slug", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> uploadPublication(
			@RequestParam(name="title", required = false) String title,
			@RequestParam(name="slug", required = false) String slug,
            @RequestParam(name="file", required = false) MultipartFile file,
            @RequestParam(name="content", required = false) String content,
            @RequestParam(name="author", required = false) int author,
            @RequestParam(name="tags", required = false) int[] tags,
            @RequestParam(name="featuredImg", required = false) MultipartFile featuredImg
			) {
		System.out.println("Pogodio sam metodu uploadPublication()");
		System.out.println("Uploadovao sam file: "+file.getOriginalFilename());
		System.out.println("Body: "+content);
		System.out.println("Author id: "+author);
		System.out.println("Featured img: "+featuredImg.getOriginalFilename());
		
		Validation validation = validator.validatePostFields(title, slug, author, file);
		
		if(!validation.hasErrors()) {
			boolean published = publicationService.createAndUploadPublication(title, slug, content, author, tags, file);
			if(published) {
				return new ResponseEntity<Object>("Successful submit!!", HttpStatus.OK);
			}else {
				validation.setHasErrors(true);
				validation.setErrorMessage("Greska pri upload-u datoteke!");
				return new ResponseEntity<Object>(validation, HttpStatus.SERVICE_UNAVAILABLE);
			}			
		}else {			
			return new ResponseEntity<Object>(validation, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
