package org.sobakaisti.mvt.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.AuthorProfile;
import org.sobakaisti.mvt.service.AuthorService;
import org.sobakaisti.util.CommitResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="/profile/edit/{authorId}", method=RequestMethod.GET)
	public String insertAuthorProfileEditPage(@PathVariable int authorId, Model model) {		
		if (!model.containsAttribute("profile")) {
			Author author = authorService.findFull(authorId);
			if(author != null) {
				model.addAttribute("profile", author.getProfile());
				logger.info("Ucitavam profil autora: "+author.getFullName()+" za izmenu.");
			} else
				model.addAttribute("profile", new AuthorProfile());	
		}
		return "dashboard/profile_fragments :: editableProfileFragment";
	}
	
	
	@RequestMapping(value="/profile/edit/submit", method=RequestMethod.POST)
	public String submitEditedAuthorProfile(@Valid @ModelAttribute("profile") AuthorProfile profile, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			System.out.println("Ima gresaka!");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profile", result);
			redirectAttributes.addFlashAttribute("profile", profile);
			redirectAttributes.addFlashAttribute("FORM_REQUIRED", true);
			return "dashboard/profile";
		} else {
			profile = authorService.saveOrUpdateProfile(profile);			
			String commitMessage = (profile != null) ? "Uspesno ste azurirali profil" 
				: "Dogodila se greska prilikom azuriranja profila!";	
			redirectAttributes.addFlashAttribute("commitResult", new CommitResult(profile != null, commitMessage));
		}		
		return String.format("redirect:/sbk-admin/sobakaisti/profile/%s", 
				     profile.getAuthor() != null ? profile.getAuthor().getSlug() : "");
	}
	
	@RequestMapping(value="/profile/{profileId}/social/{snId}/remove", method=RequestMethod.DELETE)
	public String removeProfilesSocialNetwork(@PathVariable int profileId, @PathVariable int snId, Model model) {
		authorService.commitProfilSocialNetworkDeletion(profileId, snId);
		return null;
	}
	
}
