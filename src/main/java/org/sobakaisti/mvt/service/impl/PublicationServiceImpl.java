/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.List;

import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
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

	@Override
	public boolean deletePublicationById(int id) {
		//TODO uvesti metodu koja uklanja item sa amazona
		return publicationDao.deletePublicatinById(id);
	}
	
	//TODO umesto hardcodovanih poruka dohvati ih is resursa!
	//TODO podigni metodu u eku univerzalniju nadklasu za sve postove!!
	@Override
	public String switchPublicationStatus(int id) {
		int status = publicationDao.switchPublicationStatus(id);
		if(status == ArticleService.ACTIVE){
			return "Uspesno ste publikovali Izdanje.";
		}else if(status == ArticleService.INACTIVE) {
			return "Uspesno ste deaktivirali izdanje.";
		}
		return null;
	}

	@Override
	public int countPublicationsByStatus(boolean isActive) {
		// TODO Auto-generated method stub
		return publicationDao.countPublicationsByStatus(isActive);
	}

	@Override
	public List<Publication> findAllPublicationsByStatus(String status) {
		List<Publication> publications;
		if(status.equals(ACTIVE_STATUS)) {
			publications = publicationDao.findAllPublicationsByStatus(1);	
		}else {
			publications = publicationDao.findAllPublicationsByStatus(0);
		}
		return publications;
	}


}
