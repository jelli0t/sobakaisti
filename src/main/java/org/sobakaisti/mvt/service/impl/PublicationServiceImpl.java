/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.List;

import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *
 */
@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationDao publicationDao;
	
	@Override
	public List<Publication> findAllOrderedPublications() {
		return publicationDao.findAllOrderedPublications();
	}

}
