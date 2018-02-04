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
}
