/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//		model.addAttribute("name", "Sobakaisti");
//		model.addAttribute("authors", authorDaoImpl.getAllAuthors());
		return "dash_authors";
	}
	@RequestMapping(value="/sobakaisti/add", method=RequestMethod.POST)
	@ResponseBody
	public Author addNewSoakais(@RequestBody Author author){
		System.out.println(author);
		return author;
	}
	
	@ResponseBody
	@RequestMapping(value="/sobakaisti/test", method=RequestMethod.POST)
	public ResponseEntity<String> testModel(@RequestBody TestModel testModel){
		System.out.println("TEST: "+testModel.getName()+"; "+testModel.getComment()+"; Date: "+testModel.getDate());
		return new ResponseEntity<String>("Uspesno!", HttpStatus.OK);
	}

//	@RequestMapping(value="/sobakaisti/test", method=RequestMethod.POST)
//	@ResponseBody	
//	public String testAjaxRequest(@RequestBody String name){
//		System.out.println("NAME: "+name);
//		return name;
//	}

}
