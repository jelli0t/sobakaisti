/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.List;

import org.sobakaisti.mvt.models.Publication;

/**
 * @author NEKS Office
 *
 */
public interface PublicationDao {

	public List<Publication> findAllOrderedPublications();
	
	/**
	 * Brise izdanje na osnovu ID-a
	 * @param int
	 * */
	public boolean deletePublicatinById(int id);

	public int switchPublicationStatus(int id);
	
	/**
	 * Probroj izdanja prema statusu
	 * @param boolean
	 * TODO podigni metodu u superklasu
	 * */
	public int countPublicationsByStatus(boolean isActive);

	/**
	 * Dohvata sve publikacije sa zadatim statusom
	 * @param int status
	 * */
	public List<Publication> findAllPublicationsByStatus(int status);
}
