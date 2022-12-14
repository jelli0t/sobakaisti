/**
 * 
 */
package org.sobakaisti.mvt.dao.impl;

import org.hibernate.Query;
import org.sobakaisti.dao.AbstractPostDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.i18n.model.I18nPublication;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jelli0t
 *
 */
@Repository
public class PublicationDaoImpl extends AbstractPostDao<Publication, I18nPublication> implements PublicationDao {

	@Override
	@Transactional
	public int updateAndCountDownloads(int publicationId) {
			int downloadsAfter = 0;
			String HQL = "update Publication p set p.downloaded = p.downloaded + 1 where p.id = :id";
			try {
				Query query = currentSession().createQuery(HQL);
				query.setParameter("id", publicationId);
				int updated = query.executeUpdate();
				if(updated > 0) {
					downloadsAfter = (Integer) currentSession().createQuery("select p.downloaded from Publication p where p.id = :id")
						.setParameter("id", publicationId).uniqueResult();
					logger.info("Uspesno azuriran broj preuzimanja na vrednost: "+downloadsAfter+" za Publication sa id:"+publicationId);
				}
			} catch (Exception e) {
				logger.warn("Greska prilikom azuriranja broja preuzimanja za Publication sa ID: "+publicationId);
			}
		return downloadsAfter;
	}

}