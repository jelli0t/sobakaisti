/**
 * 
 */
package org.sobakaisti.mvt.i18n.dao;

import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Post;

/**
 * @author Korisnik
 *
 */
public interface I18nPostDao<P extends Post, I extends I18nPost> {

	/**
	 * Dohvata prevedeni post na osnovu slua -a
	 * za zadati jezika
	 * */
	public P getTranslatedPostBySlug(String slug, String lang);
	
	/**
	 * Dohvata prevod post sa odredjenim ID na jeziku 
	 * ciji lang code saljemo kao argument
	 * @param id	ID postojeceg posta na default jeziku
	 * @param lang	Lang code: en / es / de...
	 * @return vraca preveden post ili null ukoliko prevod ne postoji
	 * */
	public P getTranslatedPostById(int id, String lang);
	
	
	public I saveOrUpdate(I i);
}
