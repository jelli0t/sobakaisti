package org.sobakaisti.mvt.validation;

import org.springframework.web.multipart.MultipartFile;

public interface Validator {
	
	public static final int ZERO_AUTHOR = 0;
	public static final String TITLE_FIELD_NAME = "title";
	public static final String SLUG_FIELD_NAME = "slug";
	public static final String AUTHOR_FIELD_NAME = "author";
	public static final String FILE_FIELD_NAME = "file";

	public Validation validatePostFields(String title, String slug, int authorId, MultipartFile file);
}
