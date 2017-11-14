package org.sobakaisti.mvt.service;

import java.util.Calendar;

import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MediaPostFactory extends PostFactory{

	@Override
	public Post processPostRequest(PostRequest postRequest) {
		Media media = null;		
		if(postRequest != null && postRequest.getMedia() != null) {
			media = new Media();
					
			
			final MultipartFile mediaFile = postRequest.getMedia();
			
			media.setTitle(mediaFile.getName());
			media.setSlug(mediaFile.getName());
			media.setPostDate(Calendar.getInstance());
			
			media.setContentType(mediaFile.getContentType());
			media.setFileName(mediaFile.getOriginalFilename());
			media.setSize(mediaFile.getSize());
		}
		System.out.println(media);
		return media;
	}

}
