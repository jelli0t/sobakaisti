/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jelli0t
 *
 */
@Controller
@RequestMapping(value="/sbk-admin/tags")
public class TagsController {

	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/search", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<List<Tag>> searchArticleTags(@RequestBody String hint){
		List<Tag> tags = tagService.findListOfTagsByHint(hint);
		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Tag> saveInputAsTag(@RequestBody String phrase){
		System.out.println("Trazena fraza: "+phrase);
		Tag tag = tagService.findOrCreateTagFromPhrase(phrase);
		if(tag != null) {
			return new ResponseEntity<Tag>(tag, HttpStatus.OK);
		}else {
			return new ResponseEntity<Tag>(tag, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
