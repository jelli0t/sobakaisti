/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.MediaService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.validation.Validation;
import org.sobakaisti.mvt.validation.Validator;
import org.sobakaisti.util.CalendarUtil;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostRequest;
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
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
		
	@Autowired
	private AuthorDao authorDao;	
	@Autowired
	private ArticleService articleService; 
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private Validator validator;
	private Pagination pagination;
	
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
			author.setSlug(StringUtil.makeSlug(fullName));
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
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTestPage(Model model){
		
		model.addAttribute("post", new PostRequest());
		model.addAttribute("categories", categoryDao.findAllCategories());
		
		return "dashboard/dash_test";
	}
	
	
	@RequestMapping(value="/article/post", method=RequestMethod.POST)
	public String postArticle(@ModelAttribute("post") PostRequest post, Model model) {
		
		System.out.println("UPLOAD: "+ post);
		model.addAttribute("post", post);
		return "dashboard/dash_test";		
	}
	
	
	
	@RequestMapping(value="/slug/new", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> createNewArticleSlugFromTitle(@RequestBody String title){
		System.out.println("title: "+title);
		if(title==null || title.equals("NO_DATA")){
			return new ResponseEntity<String>("Naslov poruke je prazan!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try{
			String slug = StringUtil.makeSlug(title);
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
	
	/**
	 * Prikazuje listu clanaka
	 * @param page 	page index
	 * */
//	@RequestMapping(value = {"/articles", "/articles/{page}"}, method=RequestMethod.GET)
//	public String displayAllArticles(@PathVariable Optional<Integer> page, Model model){	
//		/* ako je odabrana strana */
//		if(page.isPresent()) {
//			pagination = new Pagination(0, page.get().intValue(), Pagination.DEFAULT_ITEMS_PER_PAGE);			
//		}else {
//			pagination = new Pagination();
//		}
//		/* priprema model atribute za renderovanje strane */
//		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, null);
//		for(String key : modelAttributes.keySet()) {
//			model.addAttribute(key, modelAttributes.get(key));
//		}
//		return "dashboard/dash_articles";
//	}
	
	@Deprecated
	@RequestMapping(value = "/articles", method=RequestMethod.GET)
	public String displayAllArticles(Model model){	
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(new Pagination(), null, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_articles";
	}
	
	@Deprecated
	@RequestMapping(value = "/articles/{page}", method=RequestMethod.GET)
	public String displayAllArticles(@PathVariable int page, Model model){	
		/* ako je odabrana strana */
		pagination = new Pagination(0, page, Pagination.DEFAULT_ITEMS_PER_PAGE);		
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_articles";
	}
	
	
	/**
	 * Prikazuje listu clanaka filtrirnih po odbranom statusu
	 * @param page 		page index
	 * @param status	active/nonactive
	 * */
//	@RequestMapping(value = {"/articles/status/{status}", "/articles/{page}/status/{status}"}, 
//					method=RequestMethod.GET)
//	public String showArticlesByActiveStatus(@PathVariable Optional<Integer> page, @PathVariable("status") String status, Model model) {
//		if(page.isPresent()) {
//			pagination = new Pagination(0, page.get().intValue(), Pagination.DEFAULT_ITEMS_PER_PAGE);			
//		}else {
//			pagination = new Pagination();
//		}		
//		/* priprema model atribute za renderovanje strane */
//		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, status, null);
//		for(String key : modelAttributes.keySet()) {
//			model.addAttribute(key, modelAttributes.get(key));
//		}		
//		return "dashboard/dash_articles";
//	}
	
	@Deprecated
	@RequestMapping(value = "/articles/status/{status}", method=RequestMethod.GET)
	public String showArticlesByActiveStatus(@PathVariable("status") String status, Model model) {	
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles( new Pagination(), status, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}		
		return "dashboard/dash_articles";
	}
	
	@Deprecated
	@RequestMapping(value = "/articles/{page}/status/{status}", method=RequestMethod.GET)
	public String showArticlesByActiveStatus(@PathVariable int page, @PathVariable("status") String status, Model model) {
		pagination = new Pagination(0, page, Pagination.DEFAULT_ITEMS_PER_PAGE);		
		///* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, status, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}		
		return "dashboard/dash_articles";
	}
	
	
	/**
	 * Prikazuje listu clanaka filtriranih prema odabranom autoru
	 * @param page	br.strane
	 * @param authorSlug
	 * @param model
	 * */
//	@RequestMapping(value = {"/articles/by/{authorSlug}", "/articles/{page}/by/{authorSlug}"}, 
//			method=RequestMethod.GET)
//	public String showArticlesFlteredByAuthor(@PathVariable Optional<Integer> page, 
//											  @PathVariable("authorSlug") String authorSlug, 
//											  Model model) {		
//		if(page.isPresent()) {
//			pagination = new Pagination(0, page.get().intValue(), Pagination.DEFAULT_ITEMS_PER_PAGE);			
//		}else {
//			pagination = new Pagination();
//		}
//		/* priprema model atribute za renderovanje strane */
//		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, authorSlug);
//		for(String key : modelAttributes.keySet()) {
//			model.addAttribute(key, modelAttributes.get(key));
//		}
//		return "dashboard/dash_articles";
//	}
	
	@Deprecated
	@RequestMapping(value = "/articles/by/{authorSlug}", 	method=RequestMethod.GET)
	public String showArticlesFlteredByAuthor(@PathVariable("authorSlug") String authorSlug,  Model model) {		
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(new Pagination(), null, authorSlug);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_articles";
	}
	
	@Deprecated
	@RequestMapping(value = "/articles/{page}/by/{authorSlug}", method=RequestMethod.GET)
	public String showArticlesFlteredByAuthor(@PathVariable int page, 
											  @PathVariable("authorSlug") String authorSlug, 
											  Model model) {		

		pagination = new Pagination(0, page, Pagination.DEFAULT_ITEMS_PER_PAGE);
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, authorSlug);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
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
			@RequestParam(name="id", required = false) int id,
			@RequestParam(name="title", required = false) String title,
			@RequestParam(name="slug", required = false) String slug,
			@RequestParam(name="postDate", required = false) Date postDate,
            @RequestParam(name="content", required = false) String content,
            @RequestParam(name="author", required = false) int author,
            @RequestParam(name="categories", required = false) int[] categories,
            @RequestParam(name="tags", required = false) int[] tags,
            @RequestParam(name="featuredImg", required = false) MultipartFile featuredImg,
            @RequestParam(name="active") int active
			) {
		Validation validation = validator.basicPostValidation(title, slug, author);
		if(!validation.hasErrors())
			validation = validator.featuredImageFileValidation(featuredImg);
		
		if(!validation.hasErrors()) {
			boolean published = articleService.createAndUploadArticle(id, title, slug, postDate, content, author, categories, tags, featuredImg, active);
			if(published) {
				return new ResponseEntity<Object>("Uspesno ste "+(active == 1 ? "publikovali":"sacuvali kao nacrt")+" clanak.", HttpStatus.OK);
			}else {
				validation.setHasErrors(true);
				validation.setErrorMessage("Greska pri upload-u datoteke!");
				return new ResponseEntity<Object>(validation, HttpStatus.SERVICE_UNAVAILABLE);
			}			
		}else {			
			return new ResponseEntity<Object>(validation, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value="/article/edit/{id}", method=RequestMethod.GET)
	public String editArticleById(Model model, @PathVariable("id") int id) {
		Article editable = articleService.findById(id);
		model.addAttribute("categories", categoryDao.findAllCategories());
		model.addAttribute("article", editable);
		return "dashboard/dash_article";
	}
	
	//	PUBLICATIONS
	
//	TODO za finalnu produkciju
//	@RequestMapping(value = {"/publications", "/publications/{page}"}, method=RequestMethod.GET)
//	public String showPublicationsPage(@PathVariable("page") Optional<Integer> page, Model model) {		
//		if(page.isPresent()) {
//			pagination = new Pagination(0, page.get().intValue(), Pagination.DEFAULT_ITEMS_PER_PAGE);			
//		}else {
//			pagination = new Pagination();
//		}		
//		/* dohvata broj aktivnih odnosno neaktivnih izdanja */		
//		final int active = publicationService.countPublicationsByStatus(true);
//		final int nonActive = publicationService.countPublicationsByStatus(false);
//		model.addAttribute("activeCount", active);
//		model.addAttribute("nonActiveCount", nonActive);
//		model.addAttribute("publications", publicationService.findAllOrderedPublications());
//		model.addAttribute("isActive", true);
//		return "dashboard/dash_publications";
//	}
	
	//TODO za java 1.7
	@RequestMapping(value = "/publications", method=RequestMethod.GET)
	public String showPublicationsPage(Model model) {	
		Map<String, Object> modelAttributes = publicationService.prepareModelAttributesForArticles(new Pagination(), null, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_publications";
	}
	
	//TODO za java 1.7
	@RequestMapping(value = "/publications/{page}", method=RequestMethod.GET)
	public String showPublicationsPage(@PathVariable("page") int page, Model model) {			
		pagination = new Pagination(0, page, Pagination.DEFAULT_ITEMS_PER_PAGE);				
		Map<String, Object> modelAttributes = publicationService.prepareModelAttributesForArticles(pagination, null, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_publications";
	}
	
	
	@RequestMapping(value="/publications/status/{status}", method=RequestMethod.GET)
	public String showPublicationByActiveStatus(@PathVariable("status") String status, Model model) {
		if(status != null && !status.isEmpty()) {
			Map<String, Object> modelAttributes = publicationService.prepareModelAttributesForArticles( new Pagination(), status, null);
			for(String key : modelAttributes.keySet()) {
				model.addAttribute(key, modelAttributes.get(key));
			}
		}
		return "dashboard/dash_publications";
	}
	
	@RequestMapping(value="/publications/by/{authorSlug}", method=RequestMethod.GET)
	public String showpublicationsByAuthor(@PathVariable("authorSlug") String authorSlug, Model model) {
		if(authorSlug != null && !authorSlug.isEmpty()) {
			Map<String, Object> modelAttributes = publicationService.prepareModelAttributesForArticles( new Pagination(), null, authorSlug);
			for(String key : modelAttributes.keySet()) {
				model.addAttribute(key, modelAttributes.get(key));
			}
		}
		return "dashboard/dash_publications";
	}
	
	@RequestMapping(value="/publication", method=RequestMethod.GET)
	public String showNewPublicationPage(Model model) {
		
		if(!model.containsAttribute("uploaded")) {
			PostRequest postRequest = new PostRequest();
//			postRequest.setContent(uploaded.getContent());
			model.addAttribute("postRequest", postRequest);
			model.addAttribute("uploaded", new Publication());
			System.out.println("sadrzi: uploaded");
		} else {
			
			System.out.println("ne sadrzi: uploaded");
		}
		
		
//		if(uploaded != null && uploaded.getId() != 0) {
//			model.addAttribute("uploaded", uploaded);
//			System.out.println("Uploaded: "+uploaded);
//			
//		}
		
		return "dashboard/dash_publication";
	}
	
	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submitPublication(@ModelAttribute PostRequest postRequest, Model model) {
		
		Publication publication = publicationService.processAndSavePostRequest(postRequest);
		model.addAttribute("publication", publication);
		
		return "redirect:/sbk-admin/publication";
	}
	
	
	@RequestMapping(value="/datetime/update/date/{month}", method=RequestMethod.GET)
	@ResponseBody
	public int updateDateListAccordingToMonth(@PathVariable("month") int month) {
		logger.info("Dohvatam max broj dana za "+month+". mesec.");
		return CalendarUtil.getMaxDatePerMonth(month);
	}
	
	@RequestMapping(value="/datetime/editor", method=RequestMethod.GET)
	public String dispalyDateTimeEdior(@RequestParam("date") String date, Model model){
		Calendar dateTime = CalendarUtil.getInstance().parseCalendarFromString(date, CalendarUtil.INPUT_DATETIME_FORMAT);		
		model.addAttribute("date", dateTime);
		logger.info("Otvaram datetime editor i postavljam datum: "+dateTime.getTime());
		return "commons/fragments :: dateTimeEditorFragment";
	}
}
