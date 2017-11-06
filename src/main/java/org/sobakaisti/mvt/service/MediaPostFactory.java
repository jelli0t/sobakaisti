package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;
import org.springframework.stereotype.Component;

@Component
public class MediaPostFactory extends PostFactory{

	@Override
	public Post processPostRequest(PostRequest postRequest) {
		Media media = new Media();
		// TODO Auto-generated method stub
		
		
		return media;
	}

}
