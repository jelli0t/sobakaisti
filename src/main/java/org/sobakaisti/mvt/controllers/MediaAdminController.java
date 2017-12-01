package org.sobakaisti.mvt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.service.MediaService;

@Controller
@RequestMapping(value="/sbk-admin/media")
public class MediaAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MediaAdminController.class);
  
  @Autowired
	private MediaService mediaService;

}
