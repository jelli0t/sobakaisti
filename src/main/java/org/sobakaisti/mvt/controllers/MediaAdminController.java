package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.service.MediaService;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/sbk-admin/media")
public class MediaAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MediaAdminController.class);
  
  	@Autowired
	private MediaService mediaService;
		
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String showMediaSelectionByType(@RequestParam("type") String type, Model model) {			
		if(Media.MediaType.contains(type)) {
			model.addAttribute("type", type);
			logger.info("Prosledjen parametar tip: "+type+", ucitavam fragent: 'mediaSelectionFragment'");
		}		
		return "commons/fragments :: mediaSelectionFragment";
	}
	
		
	@RequestMapping(value="/library/{tab}/{type}", method=RequestMethod.GET)
	public String switchMediaSelectionBodyContent(@PathVariable String tab, @PathVariable String type, Model model) {
		String returns = "commons/fragments :: ";
		if(Media.MediaType.contains(type)) {
			if(tab.equals("upload")) {		
				returns += "mediaUploadFragment";
				logger.info("Odabran tab: "+tab+", ucitavam fragment: 'mediaUploadFragment'");
			} 
			else if(tab.equals("repo")) {
				List<Media> medias = mediaService.findAll();
				model.addAttribute("medias", medias);
				returns += "mediaRepoFragment";
				logger.info("Odabran tab: "+tab+", ucitavam fragment: 'mediaRepoFragment'");
			}
			returns += "(type='%s')";
			return String.format(returns, type);
		}
		logger.warn("Nije Prosledjen parametar! Podrazumevano ucitavam fragent: 'mediaRepoFragment'");
		return showMediaSelectionByType(type, model);		
	}
	
	
	@RequestMapping(value="/upload/{mediaType}", method=RequestMethod.POST)
	public String uploadMediaFile(@RequestParam(name="media") MultipartFile media, @PathVariable("mediaType") String mediaType, Model model) {
		logger.info("MultipartFile uploaded name: '"+media.getOriginalFilename() +"'. MediaType: "+mediaType);
		Media postMedia = null;
		/* ako je uploadovana datoteka manja od 30MB */
		if(media.getSize() < 31457300) {
			PostRequest postRequest = new PostRequest(media);
			postMedia = mediaService.processAndSavePostRequest(postRequest);
			postMedia.setUploadResultMessage("Uspesno ste otpremili datoteku.");
			postMedia.setPosted(true);
			postMedia.setMediaType(Media.MediaType.getMediaType(mediaType));
		} else {
			postMedia = new Media();
			postMedia.setUploadResultMessage("Datoteka ne sme biti veca od 30MB!");
			postMedia.setPosted(false);
		}	
		model.addAttribute("media", postMedia);		
		return "commons/fragments :: mediaUploadedPreview";
	}
}
