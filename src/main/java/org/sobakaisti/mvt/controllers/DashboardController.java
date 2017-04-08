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
import org.sobakaisti.mvt.service.ArticleService;
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
	
	@RequestMapping(value="/articles/new/slug", method=RequestMethod.POST)
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
			System.out.println("Article: "+savedArticle.getTitle());
			System.out.println("content: "+savedArticle.getContent());
			System.out.println("slug: "+savedArticle.getSlug());
			System.out.println("Author name: "+savedArticle.getAuthor());
			System.out.println("kategorije: "+article.getCategories().size());
			
			return new ResponseEntity<Object>(savedArticle, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>(result.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.GET)
	public String displayAllArticles(Model model){
		model.addAttribute("articles", articleService.getArticlesOrderByDate());
		return "dashboard/dash_articles";
	}
}
