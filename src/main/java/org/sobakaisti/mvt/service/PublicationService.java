/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.models.Publication;

/**
 * @author jelles
 *
 */
public interface PublicationService {

	public static final String ACTIVE_STATUS = "active";
	public static final String NONACTIVE_STATUS = "nonactive";
	
	public List<Publication> findAllOrderedPublications();
	
	public List<Publication> findAllPublicationsByStatus(String status);
	/**
	 * Brise izdanje za zadati ID
	 * @param int
	 * */
	public boolean deletePublicationById(int id);
	
	/**
	 * Menja status na itemu
	 * TODO nappraviti univerzalnu metodu za sve postove!
	 * @param id
	 * */
	public String switchPublicationStatus(int id);
	
	/**
	 * Prebrojava izdanja na osnovu statusa
	 * */
	public int countPublicationsByStatus(boolean isActive);
	
	
}
