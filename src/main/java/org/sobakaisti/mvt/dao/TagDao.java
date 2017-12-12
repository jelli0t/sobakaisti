/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Tag;
/**
 * @author jelli0t
 *
 */
public interface TagDao {
	
	/**
	 * Pronalazi Tag po njegovom ID-u
	 * @param id
	 * */
	public Tag findById(int id);

	/**
	 * Trazi Tag na osnovu unete fraze.
	 * Ako postoji vrati ga, ako nije u bazi kreiraj i sacuvaj novi.
	 * @param phrase 
	 * */
	public Tag findOrSaveTag(Tag tag);

	/**
	 *  trazi sve Tag-ove koji pocinju zadatim strigom
	 *  @param String
	 * */
	public List<Tag> findTagsByHint(String hint);
	
	/**
	 * Dohvati listu Tagova na osnov niza njihovih ID-eva
	 * @param ids
	 * */
	public List<Tag> findListOfTagsByIds(List<Integer> ids);
}
