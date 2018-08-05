/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.dao.PublicationDao;
import org.sobakaisti.mvt.i18n.model.I18nPublication;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.ArticleService;
import org.sobakaisti.mvt.service.PostServiceImpl;
import org.sobakaisti.mvt.service.PublicationService;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.Pagination;
import org.sobakaisti.util.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jelles
 *
 */
@Service
public class PublicationServiceImpl extends PostServiceImpl<Publication, I18nPublication> implements PublicationService {
	private static final Logger logger = LoggerFactory.getLogger(PublicationServiceImpl.class);
	
	private PublicationDao publicationDao;
	
	@Autowired
	public PublicationServiceImpl(
			@Qualifier("publicationDaoImpl") PostDao<Publication, I18nPublication> postDao) {
		super(postDao);
		this.publicationDao = (PublicationDao) postDao;
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
	
	
	@Override
	public Publication processAndSaveSubmittedPost(Publication post) {
		if(post != null) {		
			/* Ako je nova publikacija formiraj slug */
			if(post.getId() == 0)
				post.setSlug(addSuffixIfDuplicateExist(post.getSlug()));			
			/* postavlja autora */
			if(post.getAuthor() != null) {
				post.setAuthor(authorDao.getAuthorById(post.getAuthor().getId()));
			}
			/* set publications Tags */
			if(post.getTags() != null && post.getTags().size() > 0) {
				post.setTags(fatchPostFullTagList(post));
			}
			/* uploaded publication file */
			if(post.getMedia() != null) {
				Media publicationMedia = mediaService.findById(post.getMedia().getId());
				post.setMedia(publicationMedia);
			}
			/* uploaded featured image */
			if(post.getFeaturedImage() != null) {
				Media featuredImageMedia = mediaService.findById(post.getFeaturedImage().getId());
				post.setFeaturedImage(featuredImageMedia);
			}
			/* set language*/
			post.setLang(getPostLanguage());
			logger.info("Cuvam: "+post);
			Publication result = publicationDao.saveOrUpdate(post);
			if (result != null) {
				result.setCommited(new Boolean(true));
				result.setCommitMessage(getMessage("publication.posted.successful"));
				return result;
			} else {
				post.setCommited(new Boolean(false));
				post.setCommitMessage(getMessage("publication.posted.failure"));
				return post;
			}
		}
		logger.warn("Nije prosledjen Publication za procesuiranje!");
		return null;
	}
	
	@Override
	public CommitResult commitDelete(int id) {
		boolean deleted = this.delete(id);
		String message = null;
		if(deleted)
			message = getMessage("publication.delete.succesful");
		else 
			message = getMessage("publication.delete.failure");
		return new CommitResult(deleted, message);
	}
	
	@Override
	public int updateAndCountDownloads(int publicationId) {
		return publicationDao.updateAndCountDownloads(publicationId);
	}
}
