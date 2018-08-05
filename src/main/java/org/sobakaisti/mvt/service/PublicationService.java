/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.Map;

import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.Pagination;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelles
 *
 */
public interface PublicationService extends PostService<Publication>{
	
	
	/**
	 * Kreira i uploaduje Publication objekat od prosledjenih parametara
	 * @param title
	 * @param slug
	 * @param content
	 * @param authorId
	 * @param tagIds
	 * @param file
	 * */
	public boolean createAndUploadPublication(String title, String slug, String content, int authorId, int[] tagIds, MultipartFile file);
	
	/**
	 *  TODO podici u abstarktnu klasu
	 *  kreira mapu za popunjavanje modela
	 *  @param pagination
	 *  @param status
	 *  @param authorSlug
	 * */
	public Map<String, Object> prepareModelAttributesForArticles(Pagination pagination, String status, String authorSlug);

	int updateAndCountDownloads(int publicationId);
	
}
