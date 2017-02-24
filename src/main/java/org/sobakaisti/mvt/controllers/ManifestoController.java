/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jelles
 *
 */
@Controller
@RequestMapping("/manifesto")
public class ManifestoController {

	@RequestMapping(method=RequestMethod.GET)
	public String displayManifestoPage(){
		
		return "manifesto";
	}
	
}
