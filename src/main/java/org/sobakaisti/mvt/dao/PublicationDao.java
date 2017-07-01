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
}
