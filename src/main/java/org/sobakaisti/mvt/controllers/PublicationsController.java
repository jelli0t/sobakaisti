/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.PostService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.StringUtil;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
	@ModelAttribute
	public void prepare(Model model){
		
		model.addAttribute(TextUtil.URL_BASIS_ATTR_NAME, "publications");
		model.addAttribute(PostService.PUBLICATION_INDICATOR_ON_ATTR, true);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showPublicationHomepage(Model model) {
		model.addAttribute("authors", publicationService.findAllPostsAuthors());
		model.addAttribute("publications", publicationService.findAllPostOrderedByDate());
		return "publications";
	}
	
	@RequestMapping(value="/by/{author}", method=RequestMethod.GET)
	public String showPublicationsByAuthor(@PathVariable("author") String author, Model model) {
		if(author != null && !author.isEmpty()) {
			Author chosenAuthor = authorDao.findAuthorBySlug(author);
			model.addAttribute("publications", publicationService.findAllOrderedPostsByAuthor(chosenAuthor));
			model.addAttribute("authors", publicationService.findAllPostsAuthors());
			model.addAttribute("chosenAuthor", chosenAuthor);
			return "publications";
		}else {
			return showPublicationHomepage(model);
		}		
	}
	
	@RequestMapping(value="/{titleSlug}", method=RequestMethod.GET)
	public String showArticleBySlug(@PathVariable("titleSlug") String titleSlug, Model model) {
		if(TextUtil.notEmpty(titleSlug)) {			
			Publication publication = publicationService.findBySlug(titleSlug);
			List<Publication> relatedPosts = publicationService.findAllOrderedPostsByAuthor(publication.getAuthor());

			model.addAttribute(TextUtil.POST_ATTR_NAME, publication);
			model.addAttribute(TextUtil.RELATED_POSTS_ATTR_NAME, relatedPosts);
			model.addAttribute("author", publication.getAuthor());
			model.addAttribute(PostService.POST_SORTING_ALLOWED_PARAM, false);
			model.addAttribute(PostService.META_CIRCLE_ALLOWED_ATTR, true);
		}
		return "article";
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
	public String switchPublicationStatus(@PathVariable("id") int id) {	
		CommitResult commited = publicationService.switchPostStatus(id);
		return String.format("commons/fragments :: commitResultFragment(commited='%s', message='%s')",
				commited.isCommited(), commited.getCommitMessage());
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
	public String uploadNewPublication(@ModelAttribute(value="postRequest") @Valid Publication publication, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.publication", bindingResult);
			redirectAttributes.addFlashAttribute("publication", publication);
			logger.warn("Ima gresaka pri validaciji!");
		} else {
			Publication uploaded = publicationService.processAndSaveSubmittedPost(publication);		
			redirectAttributes.addFlashAttribute("publication", uploaded);
			logger.info("Uspesno publikovan: "+uploaded);
		}		
		return "redirect:/sbk-admin/publication";
	}
}
