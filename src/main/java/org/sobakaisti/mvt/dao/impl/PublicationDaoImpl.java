/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import org.sobakaisti.mvt.dao.AbstractPostDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.i18n.model.I18nPublication;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.stereotype.Repository;

/**
 * @author jelli0t
 *
 */
@Repository
public class PublicationDaoImpl extends AbstractPostDao<Publication, I18nPublication> implements PublicationDao {

}