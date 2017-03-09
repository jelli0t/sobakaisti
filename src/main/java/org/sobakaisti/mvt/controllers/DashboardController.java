/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import javax.validation.Valid;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping(value="/sbk-admin")
public class DashboardController {
	
	@Autowired
	private AuthorDao authorDaoImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public String displayDashHome(){		
		return "dash_home";
	}
	
	@RequestMapping(value="/sobakaisti", method=RequestMethod.GET)
	public String showSobakaistiHome(Model model){
		model.addAttribute("name", "Sobakaisti");
		model.addAttribute("authors", authorDaoImpl.getAllAuthors());
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
			authorDaoImpl.persistAuthor(author);
			authors[0] = author;
		}		
		System.out.println(author);
		
		return new ResponseEntity<Object[]>(authors, HttpStatus.OK);
	}
	

	@RequestMapping(value="/sobakaisti/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody	
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") int id){
		try{
			authorDaoImpl.deleteAuthor(id);
			return new ResponseEntity<String>("Uspesno!", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Neuspela operacija", HttpStatus.SERVICE_UNAVAILABLE);
		}	
	}
	
	
	@RequestMapping(value="/articles/new", method=RequestMethod.GET)
	public String createNewArticle(){
		
		return "dashboard/dash_article";
	}
}
