package org.sobakaisti.mvt.validation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidatorImpl implements Validator {

	@Override
	public Validation validatePostFields(String title, String slug, int authorId, MultipartFile file) {
		
		Validation validation = new Validation(false);
		/* da li je title prazan */
		if(title == null || title.isEmpty()) {
			validation.setHasErrors(true);
			validation.setFieldName(TITLE_FIELD_NAME);
			validation.setErrorMessage("Polje ne sme biti prazno");
			return validation;
		}
		/* proverava duzinu naslova*/
		else if(title.length() < 2 || title.length() > 255) {
			validation.setHasErrors(true);
			validation.setFieldName(TITLE_FIELD_NAME);
			validation.setErrorMessage("Naslov mora sadrzati najmaje 2 a najvise 255 karaktera");
			return validation;
		}
		
		/* proverava da li je autor selektovan */
		if(authorId == 0) {
			validation.setHasErrors(true);
			validation.setFieldName(AUTHOR_FIELD_NAME);
			validation.setErrorMessage("Odaberite autora!");
			return validation;
		}
		/* provera file za upload */
		if(file == null || file.isEmpty()) {
			validation.setHasErrors(true);
			validation.setFieldName(FILE_FIELD_NAME);
			validation.setErrorMessage("Niste selektovali datoteku ua upload!");
			return validation;
		}
		/* ako je fajl preko 20Mb */
		else if(file.getSize() > 20971520) {
			validation.setHasErrors(true);
			validation.setFieldName(FILE_FIELD_NAME);
			validation.setErrorMessage("Maksimalna dozvoljena velicina dokumenta je 20MB!");
			return validation;
		}
		
		return validation;		
	}

}
