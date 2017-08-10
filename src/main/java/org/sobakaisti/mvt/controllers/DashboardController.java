/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.validation.Validation;
import org.sobakaisti.mvt.validation.Validator;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value="/sbk-admin")
public class DashboardController {
	
	@Autowired
	private AuthorDao authorDao;	
	@Autowired
	private ArticleService articleService; 
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private Validator validator;
	
	@ModelAttribute
	public void prepare(Model model){
		List<Author> authors = authorDao.getAllAuthors();
		model.addAttribute("authors", authors);
		model.addAttribute("article", new Article());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayDashHome(){		
		return "dash_home";
	}
	
	@RequestMapping(value="/sobakaisti", method=RequestMethod.GET)
	public String showSobakaistiHome(Model model){
		model.addAttribute("name", "Sobakaisti");
		model.addAttribute("authors", authorDao.getAllAuthors());
		return "dash_authors";
	}
	
	@RequestMapping(value="/sobakaisti/add", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object[]> addNewSoakais(@Valid @RequestBody Author author, BindingResult result){
		Object[] authors = new Author[1];
		if(result.hasErrors()){
			Object[] errors = result.getFieldErrors().toArray();
			return new ResponseEntity<Object[]>(errors, HttpStatus.BAD_REQUEST);			
		}else{
			String fullName = author.getFirstName() + " " + author.getLastName();
			author.setSlug(StringUtil.makeSlugFromTitle(fullName));
			authorDao.persistAuthor(author);
			authors[0] = author;
		}		
		System.out.println(author);
		
		return new ResponseEntity<Object[]>(authors, HttpStatus.OK);
	}
	

	@RequestMapping(value="/sobakaisti/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") int id){
		try{
			authorDao.deleteAuthor(id);
			return new ResponseEntity<String>("Uspesno!", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Neuspela operacija", HttpStatus.SERVICE_UNAVAILABLE);
		}	
	}
	
	
	@RequestMapping(value="/articles/new", method=RequestMethod.GET)
	public String createNewArticle(Model model){
		model.addAttribute("categories", categoryDao.findAllCategories());
		return "dashboard/dash_article";
	}
	
	@RequestMapping(value="/slug/new", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> createNewArticleSlugFromTitle(@RequestBody String title){
		System.out.println("title: "+title);
		if(title==null || title.equals("NO_DATA")){
			return new ResponseEntity<String>("Naslov poruke je prazan!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try{
			String slug = StringUtil.makeSlugFromTitle(title);
			return new ResponseEntity<String>(slug, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Neuspesna konverzija u slug", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
		
	
	@RequestMapping(value="/articles/new/save", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Object> saveNewArticle(@Valid @RequestBody Article article, BindingResult result){
		if(!result.hasErrors()){
			Article savedArticle = articleService.saveArticle(article);	
			return new ResponseEntity<Object>(savedArticle, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>(result.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
		
	@RequestMapping(value="/article/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteArticle(@PathVariable("id") int id){	
		boolean isDeleted = articleService.deleteArticleById(id);
		if(isDeleted)
			return new ResponseEntity<String>("Uspesno obrisan clanak.", HttpStatus.OK);
		
		return new ResponseEntity<String>("Greska pri brisanju.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.GET)
	public String displayAllArticles(Model model){		
		/* dohvata broj aktivnih odnosno neaktivnih izdanja */		
		final int active = articleService.countArticlesByStatus(true);
		final int nonActive = articleService.countArticlesByStatus(false);
		model.addAttribute("activeCount", active);
		model.addAttribute("nonActiveCount", nonActive);
		model.addAttribute("articles", articleService.getArticlesOrderByDate(15));
		model.addAttribute("isActive", true);
		
		return "dashboard/dash_articles";
	}
	
	@RequestMapping(value="/articles/status/{status}", method=RequestMethod.GET)
	public String showArticlesByActiveStatus(@PathVariable("status") String status, Model model) {
		if(status != null && !status.isEmpty()) {
			List<Article> articles = articleService.findAllArticlesByStatus(status);
			model.addAttribute("articles", articles);
			/* dohvata broj aktivnih odnosno neaktivnih izdanja */		
			final int active = articleService.countArticlesByStatus(true);
			final int nonActive = articleService.countArticlesByStatus(false);
			model.addAttribute("activeCount", active);
			model.addAttribute("nonActiveCount", nonActive);
			model.addAttribute("isActive", status.equals(ArticleService.ACTIVE_STATUS) ? true : false);
		}
		return "dashboard/dash_articles";
	}
	
	@RequestMapping(value="/article/change_status/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> switchArticleStatus(@PathVariable("id") int id) {	
		String message = articleService.switchArticleStatus(id);
		if(message != null){
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}		
		return new ResponseEntity<String>("Greska promeni statusa.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> postArticle(
			@RequestParam(name="title", required = false) String title,
			@RequestParam(name="slug", required = false) String slug,
            @RequestParam(name="content", required = false) String content,
            @RequestParam(name="author", required = false) int author,
            @RequestParam(name="categories", required = false) int[] categories,
            @RequestParam(name="tags", required = false) int[] tags,
            @RequestParam(name="featuredImg", required = false) MultipartFile featuredImg
			) {
		System.out.println("Pogodio sam metodu uploadPublication()");
		System.out.println("Body: "+content);
		System.out.println("Author id: "+author);
		System.out.println("Featured img: "+featuredImg.getOriginalFilename());
		
		Validation validation = validator.validatePostFields(title, slug, author, featuredImg);
		
		if(!validation.hasErrors()) {
			boolean published = articleService.createAndUploadArticle(title, slug, content, author, categories, tags, featuredImg);
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
	
	
	
	//	PUBLICATIONS
	
	@RequestMapping(value="/publications", method=RequestMethod.GET)
	public String showPublicationsPage(Model model){
		/* dohvata broj aktivnih odnosno neaktivnih izdanja */		
		final int active = publicationService.countPublicationsByStatus(true);
		final int nonActive = publicationService.countPublicationsByStatus(false);
		model.addAttribute("activeCount", active);
		model.addAttribute("nonActiveCount", nonActive);
		model.addAttribute("publications", publicationService.findAllOrderedPublications());
		model.addAttribute("isActive", true);
		return "dashboard/dash_publications";
	}
	
	@RequestMapping(value="/publications/status/{status}", method=RequestMethod.GET)
	public String showPublicationByActiveStatus(@PathVariable("status") String status, Model model) {
		if(status != null && !status.isEmpty()) {
			List<Publication> publications = publicationService.findAllPublicationsByStatus(status);
			model.addAttribute("publications", publications);
			/* dohvata broj aktivnih odnosno neaktivnih izdanja */		
			final int active = publicationService.countPublicationsByStatus(true);
			final int nonActive = publicationService.countPublicationsByStatus(false);
			model.addAttribute("activeCount", active);
			model.addAttribute("nonActiveCount", nonActive);
			model.addAttribute("isActive", status.equals(PublicationService.ACTIVE_STATUS) ? true : false);
		}
		return "dashboard/dash_publications";
	}
	
	@RequestMapping(value="/publication", method=RequestMethod.GET)
	public String showNewPublicationPage(Model model){
		return "dashboard/dash_publication";
	}
}