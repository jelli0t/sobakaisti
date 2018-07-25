package org.sobakaisti.mvt.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.sobakaisti.mail.MailService;
import org.sobakaisti.mail.MailTemplateHelper;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.AuthorService;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.MailMessage;
import org.sobakaisti.util.PropertiesUtil;
import org.sobakaisti.util.Socials;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="/")
	public String displayHome(){		
		return "intro";  // home
	}
			
	@RequestMapping(value="/movement", method=RequestMethod.GET )
	public String showMovementHome() {
		return "mvt_intro";
	}
	
	@RequestMapping(value="/movement/load_background", method=RequestMethod.GET)
	public String loadIntroBackground(@RequestParam("width") int width, 
										@RequestParam("height") int height, 
										@RequestParam("charWidth") double charWidth, Model model) {
		String lang = articleService.getPostLanguage();
		List<String> rows = articleService.getRowsFromArticleWithDimension(width, height, charWidth, lang);
		model.addAttribute("backgroundLines", rows);
		
		return "commons/fragments :: introBackground";
	}
	
	
	@RequestMapping(value="/movement/sitemap", method=RequestMethod.GET )
	public String showSiteMap() {
		return "sitemap";
	}
	
	
	@RequestMapping(value="/authors", method=RequestMethod.GET)
	public String showAuthorsHome(Model model) {
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("quotes", authorService.getAuthorsManifestQuotes());
		return "authors";
	}
	
	@RequestMapping(value="/authors/bio/{authorSlug}", method=RequestMethod.GET)
	public String showAuthorBio(@PathVariable String authorSlug, Model model) {
		if(TextUtil.notEmpty(authorSlug)) {
			model.addAttribute("author", authorService.findBySlug(authorSlug));			
			model.addAttribute("socials", PropertiesUtil.socials.findSocialNetworkProfilesForAuthor(authorSlug));			
		}
		return "commons/fragments :: authorBioFragment";
	}
	
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String showContactPage(Model model) {
		model.addAttribute(TextUtil.CONTACT_INDICATOR_ON_ATTR, true);
		model.addAttribute(TextUtil.JS_BTTN_ON_ATTR_NAME, true);
		model.addAttribute(TextUtil.JS_BTTN_CLASS_ATTR_NAME, "js-author-contact");
		model.addAttribute(TextUtil.URL_BASIS_ATTR_NAME, "contact");
		model.addAttribute("authors", authorService.findAll());
		if (!model.containsAttribute("mailMessage")) {
			model.addAttribute("mailMessage", new MailMessage());
			System.out.println("nema message obj, pravim novi!");
		}
		return "contact";
	}
	
	
	@RequestMapping(value="/contact/message/submit", method=RequestMethod.POST)
	public String submitContactForm(@Valid @ModelAttribute("mailMessage") MailMessage mailMessage, BindingResult result,
			RedirectAttributes redirectAttributes) {
		boolean sent = false;
		if(result.hasErrors()) {
			System.out.println("Ima gresaka!");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mailMessage", result);
			redirectAttributes.addFlashAttribute("mailMessage", mailMessage);
		} else {
			System.out.println("from: "+mailMessage.getFromMail());
			mailMessage.setHtml(false);
			mailMessage.prefixMailSubject("[Kontakt forma]");	
			mailMessage.setMailTemplateName(MailTemplateHelper.CONTACT_FORM_MAIL_TEMP_NAME);
			sent = mailService.sendPlaneTextMail(mailMessage);
			String commitMessage = sent ? "Hvala. Uspesno ste poslali mail." : "Dogodila se greska prilikom slanja poruke!";
			redirectAttributes.addFlashAttribute("commitResult", new CommitResult(sent, commitMessage));
		}		
		return "redirect:/contact";
	} 
	
	
	
	@RequestMapping(value="/comment/submit", method=RequestMethod.POST)
	public String submitComment(@RequestParam(name="postId") int postId,
			@RequestParam("content") String content,
			@RequestParam(name="anonymousAuthor") String anonymousAuthor,
			Model model) {
		System.out.println("comment: "+content + "; author: "+anonymousAuthor);
		//TODO uradi custom validaciju autora i poruke
		
		if(content == null) {
			return String.format("commons/fragments :: commitResultFragment(commited=%b, message='%s')",
				false, "Greska neispravno polje!");
		} else {			
			model.addAttribute("comment", content);
			return "commons/fragments :: commentFragment";
		}		
	}
}
