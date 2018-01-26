/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import org.sobakaisti.mvt.dao.AbstractPostDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.stereotype.Repository;

/**
 * @author jelli0t
 *
 */
@Repository
public class PublicationDaoImpl extends AbstractPostDao<Publication> implements PublicationDao {

	@Override
	public Publication getTranslatedPost(String slug, String lang) {
		// TODO Auto-generated method stub
		return null;
	}

	
		
}