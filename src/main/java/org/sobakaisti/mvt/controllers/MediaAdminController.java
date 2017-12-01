package org.sobakaisti.mvt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.service.MediaService;

@Controller
@RequestMapping(value="/sbk-admin/media")
public class MediaAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MediaAdminController.class);
  
  	@Autowired
	private MediaService mediaService
		
	
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
			} else if(tab.equals("repo")) {				
				// dohvati sve medije soritane po datumu
				List<Media> medias = mediaService.findAll();
				model.addAttribute("medias", medias);
				returns += "mediaRepoFragment";
				logger.info("Odabran tab: "+tab+", ucitavam fragment: 'mediaRepoFragment'");
			}
			returns += "(type='%s')";
			return String.format(returnPrefix, type);
		}
		logger.warn("Nije Prosledjen parametar! Podrazumevano ucitavam fragent: 'mediaRepoFragment'");
		return showMediaSelectionByType(type, model);		
	}
}
