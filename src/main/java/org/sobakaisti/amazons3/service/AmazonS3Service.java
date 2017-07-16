package org.sobakaisti.amazons3.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

	public static final long FILE_MAX_SIZE = 204800;
	public static final int FILE_MAX_NAME = 255;
	public static final String SOBAKAISTI_BUCKET = "sobakaisti";
	public static final String PUBLICATIONS_FOLDER = "publications";
	public static final char SLASH = '/';
	
	public boolean uploadFile(String fileName, MultipartFile file);
}
