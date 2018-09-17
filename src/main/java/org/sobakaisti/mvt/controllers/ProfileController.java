package org.sobakaisti.mvt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sbk-admin/sobakaisti")
public class ProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private AuthorService authorService;

	@RequestMapping(value="/profile/{slug}", method=RequestMethod.GET)
	public String showAuthorProfile(@PathVariable String slug, Model model) {
		Author author = authorService.findFull(slug);
		if(author != null) {
			logger.info("Ucitavam profil autora: "+author.getFullName());
			model.addAttribute("profile", author.getProfile());
		}	
		return "dashboard/profile";
	}
	
	@RequestMapping(value="/profile/edit/{profileId}", method=RequestMethod.GET)
	public String insertAuthorProfileEditPage(@PathVariable int profileId, Model model) {
		Author author = authorService.findFull(profileId);
		if(author != null) {
			logger.info("Ucitavam profil autora: "+author.getFullName()+" za izmenu.");
			model.addAttribute("profile", author.getProfile());
		}
		return "dashboard/profile_fragments :: editableProfileFragment";
	}
	
}
