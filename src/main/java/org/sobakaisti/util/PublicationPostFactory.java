package org.sobakaisti.util;

import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.springframework.stereotype.Service;

@Service
public class PublicationPostFactory extends PostFactory {
	
	@Override
	protected Post processPostRequest(PostRequest postRequest) {
		Publication publication = null;
		
		return publication;
	}

}
