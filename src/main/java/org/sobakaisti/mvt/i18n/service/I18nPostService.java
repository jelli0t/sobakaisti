/**
 * 
 */
package org.sobakaisti.mvt.i18n.service;

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Post;

/**
 * @author Korisnik
 *
 */
public interface I18nPostService<P extends Post, I extends I18nPost> {	
		
	/**
	 * 
	 * */
	public I saveOrUpdateTranslatedPost(I i);
	
	/**
	 * Pronalazi preveden clanak za Post sa prosledjenim ID.
	 * Ako ne pronadje pravi novi objekat sa referencom na Post sa ID
	 * @param postId
	 * */
	public I findI18nPostByPostId(int postId);
}
