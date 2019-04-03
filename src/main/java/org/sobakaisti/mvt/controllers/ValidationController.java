/**
 * 
 */
package org.sobakaisti.mvt.controllers;

import javax.validation.Valid;

import org.sobakaisti.mvt.models.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author nemanja
 *
 */
@Controller(value = "/validate")
public class ValidationController {

	@RequestMapping(value="/author/add_or_update", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<FieldError> validateAuthorForm(@Valid @ModelAttribute Author author, BindingResult result) {
		if(result.hasErrors()) {
			result.getFieldError();
			return new ResponseEntity<FieldError>(result.getFieldError(), HttpStatus.BAD_REQUEST);
		}		
		return new ResponseEntity<FieldError>(HttpStatus.OK);
	}
}