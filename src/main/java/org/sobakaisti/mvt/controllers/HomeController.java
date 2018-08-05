package org.sobakaisti.mvt.controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mail.MailService;
import org.sobakaisti.mail.MailTemplateHelper;
import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.dao.impl.CommentDaoImpl;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.AuthorService;
import org.sobakaisti.mvt.service.CommentService;
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


@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private CommentService commentService;
	
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
	
	
	
	@RequestMapping(value="/comment/submit", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String submitComment(@RequestBody Comment comment, Model model) {
		System.out.println("comment: "+comment.getContent() + "; author: "+comment.getAnonymousAuthor()+";  origin: "+comment.getCommentOrigin());
		//TODO uradi custom validaciju autora i poruke
		
		if(TextUtil.isEmpty(comment.getAnonymousAuthor()) || TextUtil.isEmpty(comment.getContent())) {
			logger.warn("Polja sa komentara su prazna! Vracam poruku o gresci.");
			return String.format("commons/fragments :: commitResultFragment(commited=%b, message='%s')",
				false, "Greska neispravno polje!");
		} else {
			comment = commentService.authorizeAndSave(comment);
			model.addAttribute("comment", comment);			
			return "commons/fragments :: commentFragment";
		}		
	}
	
	@RequestMapping(value="/comment/{post_type}/{post_id}/more", method=RequestMethod.GET)
	public String loadMoreComments(@PathVariable("post_id") int postId, @PathVariable("post_type") String postType, 
			@RequestParam("loaded") int loaded, @RequestParam("max") int max, Model model) {
		logger.info("Zahtevam ucitavanje vise komentara na post sa id:"+postId);
		Post.Origin postOrigin = Origin.getByEntityName(postType);
		List<Comment> comments = commentService.commentsBundleLoad(postId, postOrigin, loaded, CommentService.COMMENTS_BUNDLE_LOAD_DEFAULT_SIZE);
		if(comments != null)
			model.addAttribute("comments", comments);

		return "commons/fragments :: bundleCommentsFragment";
	}
	
	
	@RequestMapping(value="/commit/result", method=RequestMethod.GET)
	public String ajaxCommitresultShow(@RequestParam("commited") boolean commited,
			@RequestParam("messageCode") String messageCode, Model model) {		
		System.out.println("commited: "+commited+" message: "+messageCode);
		model.addAttribute("commitResult", new CommitResult(commited, messageCode));
		return String.format("commons/fragments :: commitResultFragment(commited='%s', message='%s')",
				commited, messageCode);
	}
}
