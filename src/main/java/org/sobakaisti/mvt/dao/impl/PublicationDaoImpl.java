/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import javax.transaction.Transactional;

import org.sobakaisti.mvt.dao.PostDaoImpl;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.stereotype.Repository;

/**
 * @author jelli0t
 *
 */
@Repository
@Transactional
public class PublicationDaoImpl extends PostDaoImpl<Publication> implements PublicationDao {

	
		
}
