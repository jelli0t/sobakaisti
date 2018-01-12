/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Tag;

/**
 * @author jelli0t
 *
 */
public interface TagService {
	
	/**
	 * Pronalazi Tag po svom ID
	 * */
	public Tag findById(int id);

	/**
	 *  pravi i cuva objekat Tag od trazene fraze.
	 *  @param String 
	 *  @return Tag
	 * */
	public Tag createAndSaveTagFromString(String input);
	
	/**
	 * Trazi Tag na osnovu unete fraze.
	 * Ako postoji vrati ga, ako nije u bazi kreiraj i sacuvaj novi.
	 * @param phrase 
	 * */
	public Tag findOrCreateTagFromPhrase(String phrase);
	
	/**
	 * trazi listu predloga Tagova koj pocinju na uneti string
	 * @param String hint
	 * */
	public List<Tag> findListOfTagsByHint(String hint);
	
	/**
	 * Dohvati listu Tagova na osnov niza njihovih ID-eva
	 * @param ids
	 * */
	public List<Tag> findListOfTagsByIdsArray(int[] ids);
	
	/***/
	public List<Tag> findListOfTagsByIdsList(List<Integer> tagIds);
}
