/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.CategoryDao;
import org.sobakaisti.mvt.i18n.model.I18nArticle;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.util.CalendarUtil;
import org.sobakaisti.util.CommitResult;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	private Pagination pagination;
	
	@ModelAttribute
	public void prepare(Model model){
		List<Author> authors = authorDao.getAllAuthors();
		model.addAttribute("authors", authors);
		model.addAttribute("nonDefaultLangs", StringUtil.NON_DEFAULT_LANGS);
		model.addAttribute("defaultLang", StringUtil.DEFAULT_LANG_CODE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayDashHome(){		
		return "dash_home";
	}
	
	@RequestMapping(value="/sobakaisti", method=RequestMethod.GET)
	public String showSobakaistiHome(Model model){
		if(!model.containsAttribute("author")) {
			model.addAttribute("author", new Author());
		}
		model.addAttribute("name", "Sobakaisti");
		model.addAttribute("authors", authorDao.getAllAuthors());		
		return "dash_authors";
	}
	
	@RequestMapping(value="/sobakaisti/add", method=RequestMethod.POST)
	public String addNewAuthor(@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes) {		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.author", result);
			redirectAttributes.addFlashAttribute("author", author);
			redirectAttributes.addFlashAttribute("HAS_ERROR", true);
			logger.warn("Ima gresaka pri validaciji!");
		} else {
			/* Make unique authors slug */			
			author.setSlug(StringUtil.makeSlug(author.getFullName()));
			authorDao.persistAuthor(author);
			logger.info("Uspesno sacuvan novi autor: "+author);
			redirectAttributes.addFlashAttribute("author", author);
		}
		return "redirect:/sbk-admin/sobakaisti";
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
	
	@RequestMapping(value={"/sobakaisti/fragment/add_or_update_dialog/{id}", 
			"/sobakaisti/fragment/add_or_update_dialog/"}, method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
	public String callAddUpdateAuthorsDialog(@PathVariable Optional<Integer> id,  Model model) {
		Author author = null;
		if(id.isPresent()) {
			author = authorDao.findFull(id.get());
		} else 
			author = new Author();
		model.addAttribute("author", author);
		return "dashboard/dash_fragments :: authorRegistrationFragment";
	}
	
	
	@RequestMapping(value="/sobakaisti/add_or_update", method=RequestMethod.POST)
	public String addOrUpdateAuthor(@ModelAttribute Author author) {
		if(author != null) {		
			boolean saved = authorDao.saveOrUpdate(author);
			//TODO notification message kao fles attr
		}
		return "redirect:/sbk-admin/sobakaisti";
	}
	
	
	@RequestMapping(value="/validate/author/add_or_update", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<FieldError> validateAuthorForm(@Valid @ModelAttribute Author author, BindingResult result) {
		if(result.hasErrors()) {
			result.getFieldError();
			return new ResponseEntity<FieldError>(result.getFieldError(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<FieldError>(new FieldError("author", "", ""), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTestPage(Model model){
		
		model.addAttribute("post", new PostRequest());
		model.addAttribute("categories", categoryDao.findAllCategories());
		
		return "dashboard/dash_test";
	}
	
	/*
	 * 	ARTICLES
	 * */
	
	@RequestMapping(value="/articles/new", method=RequestMethod.GET)
	public String createNewArticle(Model model) {				
		if(!model.containsAttribute("article")) {
			model.addAttribute("article", new Article());
			System.out.println("Ne sadrzi Article! pravim novi...");
		}
		model.addAttribute("categories", articleService.findAllCategories());
		logger.info("from model: "+model.toString());
		return "dashboard/dash_article";
	}
	
	@RequestMapping(value="/article/trans/{langCode}/{postId}", method=RequestMethod.GET)
	public String showPostTransaltionForm(@PathVariable("langCode") String langCode, @PathVariable("postId") int postId, Model model) {
		logger.info("Clanak sa ID:"+langCode+" prevodimo na lang: "+langCode);
		I18nArticle i18nPost = null;
		if(!model.containsAttribute("i18nPost")) {		
			i18nPost = articleService.findI18nPostByPostId(postId, langCode, true);
			if(i18nPost == null) {
				i18nPost = new I18nArticle();
				Article post = articleService.findById(postId);
				i18nPost.setArticle(post);
			}
			model.addAttribute("i18nPost", i18nPost);
			System.out.println("Ne sadrzi Article! pravim novi...");
		}
		model.addAttribute("translationCode", langCode);
		return "dashboard/dash_i18nPost";
	}
	
	
	@RequestMapping(value="/article/submit", method=RequestMethod.POST)
	public String submitArticle(@ModelAttribute("article") @Valid Article article, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes, Locale locale) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.article", bindingResult);
			redirectAttributes.addFlashAttribute("article", article);
			logger.warn("Ima gresaka pri validaciji!");
			logger.warn(article.toString());
		} else {
			Article posted = articleService.processAndSaveSubmittedPost(article);
			redirectAttributes.addFlashAttribute("article", posted);
			logger.info("Uspesno publikovan: "+article);
		}
		String localeLang = (locale != null && !locale.getLanguage().equals(StringUtil.DEFAULT_LANG_CODE)) ?
				(locale.getLanguage()+"/") : "";
		return String.format("redirect:/%ssbk-admin/articles/new", localeLang);		
	}
	
	
	@RequestMapping(value="/article/{lang}/submit", method=RequestMethod.POST)
	public String submitArticleTranslation(@ModelAttribute("i18nPost") @Valid I18nArticle i18nPost, BindingResult bindingResult, 
			@PathVariable("lang") String lang, RedirectAttributes redirectAttributes, Locale locale) {
		
		logger.info("Uhvacen preveden: "+i18nPost);
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.i18nArticle", bindingResult);
			redirectAttributes.addFlashAttribute("i18nPost", i18nPost);
			logger.warn("Ima gresaka pri validaciji!");
		} else {
			if(i18nPost.getArticle() != null) 
				i18nPost.setArticle(articleService.findById(i18nPost.getArticle().getId()));
			I18nArticle translated = articleService.saveOrUpdateTranslatedPost(i18nPost);
			redirectAttributes.addFlashAttribute("i18nPost", translated);
			logger.info("Uspesno sacuvan: "+i18nPost);
		}
		redirectAttributes.addFlashAttribute("translationCode", lang);
		String localeLang = (locale != null && !locale.getLanguage().equals(StringUtil.DEFAULT_LANG_CODE)) ?
				(locale.getLanguage()+"/") : "";
		return String.format("redirect:/%ssbk-admin/article/trans/%s/%s", localeLang, lang, i18nPost.getArticle().getId());	 
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
	public String deleteArticle(@PathVariable("id") int id) {
		CommitResult commited = articleService.commitDelete(id);		
		return String.format("commons/fragments :: commitResultFragment(commited='%s', message='%s')",
				commited.isCommited(), commited.getCommitMessage());
	}
	
	/**
	 * Prikazuje listu clanaka
	 * @param page 	page index
	 * */
	@RequestMapping(value = {"/articles", "/articles/{page}"}, method=RequestMethod.GET)
	public String displayAllArticles(@PathVariable Optional<Integer> page, Model model){	
		/* ako je odabrana strana */
		if(page.isPresent()) {
			pagination = new Pagination(0, page.get().intValue(), Pagination.DEFAULT_ITEMS_PER_PAGE);			
		}else {
			pagination = new Pagination();
		}
		/* priprema model atribute za renderovanje strane */
		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, null);
		for(String key : modelAttributes.keySet()) {
			model.addAttribute(key, modelAttributes.get(key));
		}
		return "dashboard/dash_articles";
	}
	
//	@Deprecated
//	@RequestMapping(value = "/articles", method=RequestMethod.GET)
//	public String displayAllArticles(Model model){	
//		/* priprema model atribute za renderovanje strane */
//		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(new Pagination(), null, null);
//		for(String key : modelAttributes.keySet()) {
//			model.addAttribute(key, modelAttributes.get(key));
//		}
//		return "dashboard/dash_articles";
//	}
//	
//	@Deprecated
//	@RequestMapping(value = "/articles/{page}", method=RequestMethod.GET)
//	public String displayAllArticles(@PathVariable int page, Model model){	
//		/* ako je odabrana strana */
//		pagination = new Pagination(0, page, Pagination.DEFAULT_ITEMS_PER_PAGE);		
//		/* priprema model atribute za renderovanje strane */
//		Map<String, Object> modelAttributes = articleService.prepareModelAttributesForArticles(pagination, null, null);
//		for(String key : modelAttributes.keySet()) {
//			model.addAttribute(key, modelAttributes.get(key));
//		}
//		return "dashboard/dash_articles";
//	}
	
	
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
	public String switchArticleStatus(@PathVariable("id") int id) {	
		CommitResult commited = articleService.switchPostStatus(id);
		return String.format("commons/fragments :: commitResultFragment(commited='%s', message='%s')",
				commited.isCommited(), commited.getCommitMessage());
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
		
		if(!model.containsAttribute("publication")) {
			model.addAttribute("publication", new Publication());
		} 		
		return "dashboard/dash_publication";
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
