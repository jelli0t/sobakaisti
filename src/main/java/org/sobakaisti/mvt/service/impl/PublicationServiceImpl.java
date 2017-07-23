/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelles
 *
 */
@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationDao publicationDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private TagService tagService;
	
	@Override
	public List<Publication> findAllOrderedPublications() {
		return publicationDao.findAllOrderedPublications();
	}

	@Override
	public List<Publication> findAllOrderedPublicationsByAuthor(Author author) {
			return publicationDao.findAllPublicationByAuthor(author);
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

	@Override
	public boolean createAndUploadPublication(String title, String slug, String content, int authorId, int[] tagIds,
			MultipartFile file) {
		Publication publication = new Publication();
		publication.setTitle(title);
		publication.setSlug(addSuffixIfDuplicateExist(slug));
		publication.setContent(content != null ? content : "");
		publication.setPostDate(Calendar.getInstance());
		publication.setActive(1);
		/* ako je prosledjen ID autora pronadji ga i postavi ga kao autora izdanja */
		if(authorId > 0) {
			Author author = authorDao.getAuthorById(authorId);
			publication.setAuthor(author);
		}
		/* ako ima odabranih Tagova dodaj ih na publication obj. */
		if(tagIds != null && tagIds.length > 0) {
			publication.setTags(tagService.findListOfTagsByIdsArray(tagIds));
		}
		
		if(!file.isEmpty()) {
			String filePath = file.getOriginalFilename();
			publication.setPath(filePath);
		}
		return publicationDao.savePublication(publication);
	}


	private String addSuffixIfDuplicateExist(String slug) {
		int duplicates = publicationDao.countSlugDuplicates(slug);
		/* ako ima ponavljanja dodaj broj kao sufiks */
		if(duplicates > 0) {
			slug += ("-" + duplicates);
		}		
		return slug;
	}

	@Override
	public List<Author> findAllPublicationsAuthors() {
		return publicationDao.findAllPublicationsAuthors();
	}

	
}
