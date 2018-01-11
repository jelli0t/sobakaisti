/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final Logger logger = LoggerFactory.getLogger(TagsController.class);

	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String searchArticleTags(@RequestParam("hint") String hint, Model model){
		List<Tag> tags = tagService.findListOfTagsByHint(hint);
//		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
		model.addAttribute("tags", tags);
		return "commons/fragments :: tagBonesFragment";
	}
	
	@RequestMapping(value="/add/{phrase}", method=RequestMethod.GET)
	public String saveInputAsTag(@PathVariable("phrase") String phrase, @RequestParam("index") int index, Model model) {
		System.out.println("Trazena fraza: "+phrase);
		Tag tag = tagService.findOrCreateTagFromPhrase(phrase);
		return appendSelectedTag(tag.getId(), index, model);
	}
	
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public String appendSelectedTag(@PathVariable("id") int id, @RequestParam("index") int index, Model model) {
		System.out.println("Tag ID: "+id+"; Tag index: "+index);
		model.addAttribute("tag", tagService.findById(id));
		model.addAttribute("index", index);
		logger.info("Pronadjen tag: "+tagService.findById(id));
		return "commons/fragments :: tagBoneFragment";
	}
}
