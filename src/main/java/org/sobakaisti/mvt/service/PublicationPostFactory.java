package org.sobakaisti.mvt.service;


import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationPostFactory extends PostFactory {
	
	@Autowired
	private PublicationService publicationService;
	
	@Override
	public Post processPostRequest(PostRequest postRequest) {
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
		publication.setAuthor(postRequest.getAuthor());
		/* postavlja datum objave */
		publication.setPostDate(postRequest.getPostDate());
		/* avtive status */
		publication.setActive(postRequest.getActive());
		/* postavljamo sadrzaj publikacije */
		publication.setContent(postRequest.getContent() != null ? postRequest.getContent() : "");
		
		/* ako ima odabranih Tagova dodaj ih na publication obj. */
		if(postRequest.getTagIds() != null && postRequest.getTagIds().length > 0) {
			publication.setTags(tagService.findListOfTagsByIdsArray(postRequest.getTagIds()));
		}
		/* postavlja ime file-a */
		if(!postRequest.getPublication().isEmpty()) {
			String filePath = postRequest.getPublication().getOriginalFilename();
			publication.setPath(filePath);
		}
		System.out.println(publication);
		return publication;
	}

}
