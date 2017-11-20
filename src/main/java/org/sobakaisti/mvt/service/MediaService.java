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
	public static final String LINUX_UPLOADS_DIR = "/home/jelli0t/Pictures/uploads/"; 

	boolean uploadMediaToFileSystem(MultipartFile media, String fileName);
	
	/**
	 * Uklanja media datoteku sa file sistema kao i iz baze odgovarajuce podatke
	 * */
	boolean fullyRemoveMedia(int id);
	
	/**
	 * 
	 * */
	boolean updateMediaDetails(Media media);

}
