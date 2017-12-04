package org.sobakaisti.mvt.service;


import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationPostFactory extends PostFactory {
	private static final Logger logger = LoggerFactory.getLogger(PublicationPostFactory.class);
	
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private MediaService mediaService;
	
	@Override
	public Post processPostRequest(PostRequest postRequest) {
		logger.info("Procesuiramo "+postRequest);
		Publication publication = null;		
		if(postRequest != null) {
			publication = new Publication();			
			/* ako ima ID */
			if(postRequest.getId() != 0)
				publication.setId(postRequest.getId());
			/* postavljamo naslov */
			publication.setTitle(postRequest.getTitle());
			/* set slug*/
			publication.setSlug(publicationService.addSuffixIfDuplicateExist(postRequest.getSlug()));
			/* postavlja autora */
			if(postRequest.getAuthor() != null) {
				publication.setAuthor(authorDao.getAuthorById(postRequest.getAuthor().getId()));
			}
			/* postavlja datum objave */
			publication.setPostDate(Calendar.getInstance());
			/* avtive status */
			publication.setActive(postRequest.getActive());
			/* postavljamo sadrzaj publikacije */
			publication.setContent(postRequest.getContent() != null ? postRequest.getContent() : "");
			/* ako ima odabranih Tagova dodaj ih na publication obj. */
			if(postRequest.getTagIds() != null && postRequest.getTagIds().length > 0) {
				publication.setTags(tagService.findListOfTagsByIdsArray(postRequest.getTagIds()));
			}
			/* uploaded publication file */
			if(postRequest.getPublicationMediaId() != 0) {
				Media publicationMedia = mediaService.findById(postRequest.getPublicationMediaId());
				publication.setMedia(publicationMedia);
			}
			/* uploaded featured image */
			if(postRequest.getFeaturedImageId() != 0) {
				Media featuredImageMedia = mediaService.findById(postRequest.getFeaturedImageId());
				publication.setFeaturedImage(featuredImageMedia);
			}
			/* set language*/
			publication.setLang("rs");
		}		
		logger.info("Kreirao "+publication);
		return publication;
	}

}
