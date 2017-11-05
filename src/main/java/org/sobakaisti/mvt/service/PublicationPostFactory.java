package org.sobakaisti.mvt.service;


import java.util.Calendar;

import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PublicationPostFactory extends PostFactory {
	
	@Autowired
	private PublicationService publicationService;
	
	@Override
	public Post processPostRequest(PostRequest postRequest) {
		System.out.println("Procesuiramo postRequest: "+postRequest);
		Publication publication = new Publication();
		/* ako ima ID */
		if(postRequest.getId() != 0)
			publication.setId(postRequest.getId());
		/* postavljamo naslov */
		publication.setTitle(postRequest.getTitle());
		/* postavlja slug, uz proveru da li vec isti postoji */
		System.out.println(postRequest.getSlug() != null ? postRequest.getSlug() : "nema sluga!");
		
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
		/* postavlja ime file-a */
		if(postRequest.getPublication() != null) {
			String filePath = postRequest.getPublication().getOriginalFilename();
			publication.setPath(filePath);
		}
		/* set language*/
		publication.setLang("rs");
		
		System.out.println(publication);
		return publication;
	}

}
