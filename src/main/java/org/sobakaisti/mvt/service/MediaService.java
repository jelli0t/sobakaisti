/**
 * 
 */
package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Media;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelli0t
 *
 */
public interface MediaService extends PostService<Media> {
	  
	public static final String UPLOADS_DIR = "E://nemanja//uploads//";

	boolean uploadMediaToFileSystem(MultipartFile media, String fileName);

}
