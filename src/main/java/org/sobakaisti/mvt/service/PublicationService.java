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

	public List<Publication> findAllOrderedPublications();
}
