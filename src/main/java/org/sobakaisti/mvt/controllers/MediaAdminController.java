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
}
