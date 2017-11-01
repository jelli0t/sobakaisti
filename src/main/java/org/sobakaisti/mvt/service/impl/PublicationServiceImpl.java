/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.PostDaoImpl;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PostServiceImpl;
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
public class PublicationServiceImpl extends PostServiceImpl<Publication> implements PublicationService {

	@Autowired
	private PublicationDao publicationDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private TagService tagService;
	
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
