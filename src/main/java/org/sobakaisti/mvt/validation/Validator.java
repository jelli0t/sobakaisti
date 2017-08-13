package org.sobakaisti.mvt.validation;

import org.springframework.web.multipart.MultipartFile;

public interface Validator {
	
	public static final int ZERO_AUTHOR = 0;
	public static final String TITLE_FIELD_NAME = "title";
	public static final String SLUG_FIELD_NAME = "slug";
	public static final String AUTHOR_FIELD_NAME = "author";
	public static final String FILE_FIELD_NAME = "file";
	public static final String FEATURED_IMG_NAME = "featuredImg";

	public Validation validatePostFields(String title, String slug, int authorId, MultipartFile file);

	/**
	 * Validira osnovna polja Post objekta
	 * @param title		naslov posta
	 * @param slug		slug za url
	 * @param authorId	ID autora
	 * */
	public Validation basicPostValidation(String title, String slug, int authorId);

	/**
	 * Validira uploadovani file na novim izdanjima
	 * @param file
	 * */
	public Validation publicationFileValidation(MultipartFile file);

	/**
	 * Validira priloženu fotografiju na clanak
	 * @param file	featured image
	 * */
	public Validation featuredImageFileValidation(MultipartFile file);
}
