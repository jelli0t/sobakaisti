/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.mvt.service.TagService;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
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
		PostFilter filter = new PostFilter();
		Pagination pagination = publicationDao.createPostPagination(new Pagination(), filter);
		
		return publicationDao.findPostsSortedByDate(pagination, filter);
	}

	@Override
	public List<Publication> findAllOrderedPublicationsByAuthor(Author author) {
		
		PostFilter filter = new PostFilter(true, false, author);
		Pagination pagination = publicationDao.createPostPagination(new Pagination(), filter);
		
		return publicationDao.findPostsSortedByDate(pagination, filter);
	}
	
	@Override
	public boolean deletePublicationById(int id) {
		//TODO uvesti metodu koja uklanja item sa amazona
		return publicationDao.delete(id);
	}
	
	//TODO umesto hardcodovanih poruka dohvati ih is resursa!
	//TODO podigni metodu u eku univerzalniju nadklasu za sve postove!!
	@Override
	public String switchPublicationStatus(int id) {
		int status = publicationDao.switchActiveStatus(id);
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
		return publicationDao.countPostsByActiveStatus(isActive);
	}

	@Override
	public List<Publication> findAllPublicationsByStatus(String status) {
//		List<Publication> publications;
		PostFilter filter = new PostFilter();
		filter.setActive(status.equals(ACTIVE_STATUS));
		Pagination pagination = publicationDao.createPostPagination(new Pagination(), filter);
		
//		if(status.equals(ACTIVE_STATUS)) {			
//			publications = publicationDao.findAllPublicationsByStatus(1);	
//		}else {
//			publications = publicationDao.findAllPublicationsByStatus(0);
//		}
//		return publications;
		
		return publicationDao.findPostsSortedByDate(pagination, filter);
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
		return publicationDao.save(publication) != null ? true : false;
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
		return publicationDao.findAllPostsAuthors();
	}

	
	
	@Override
	public Map<String, Object> prepareModelAttributesForArticles(Pagination pagination, 
																String status, String authorSlug) {
		Map<String, Object> modelAttributes = new HashMap<String, Object>();
		boolean isActive = true;
		Author author = null;
		/* ako je prosledjen status */
		if(status != null)
			isActive = status.equals(ArticleService.ACTIVE_STATUS) ? true : false;
		/* ako je prosledjen autor */
		if(authorSlug != null)
			author = authorDao.findAuthorBySlug(authorSlug);
			
		PostFilter filter = new PostFilter(isActive, false, author);
		pagination = publicationDao.createPostPagination(pagination, filter);
		/* count active / nonactive posts */
		final int active = publicationDao.countPostsByActiveStatus(true);
		final int nonActive = publicationDao.countPostsByActiveStatus(false);
		/* dohvata listu clanaka */
		final List<Publication> publications = publicationDao.findPostsSortedByDate(pagination, filter);
	
		modelAttributes.put("activeCount", active);
		modelAttributes.put("nonActiveCount", nonActive);
		modelAttributes.put("publications", publications);
		modelAttributes.put("pagination", pagination);
		modelAttributes.put("isActive", isActive);
		
		return modelAttributes;
	}
}
