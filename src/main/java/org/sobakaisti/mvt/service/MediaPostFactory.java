package org.sobakaisti.mvt.service;

import java.util.Calendar;

import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MediaPostFactory extends PostFactory{
	
	@Autowired
	private MediaService mediaService;

	@Override
	public Post processPostRequest(PostRequest postRequest) {
		Media media = null;		
		if(postRequest != null && postRequest.getMedia() != null) {
			media = new Media();			
			final MultipartFile mediaFile = postRequest.getMedia();
			/* nazivi */
			if(mediaFile.getOriginalFilename() != null && !mediaFile.getOriginalFilename().isEmpty()) {
				final String fileName = mediaFile.getOriginalFilename();
				String extensionless = StringUtil.trimExtensionFromFilename(fileName);
				final String extension = StringUtil.extractFilenameExtension(fileName);
				final String slug = StringUtil.makeSlug(extensionless);
				/* postavlja user friendly naslov */
				media.setTitle(StringUtil.makeUserFriendlyTitleFromFilename(fileName));
				/* postavlja slug */
				media.setSlug(slug);
				/* original file name */
				media.setFileName(slug + extension);
			}		
			/* post date */
			media.setPostDate(Calendar.getInstance());
			/* contentType */
			media.setContentType(mediaFile.getContentType());		
			/* file size */
			media.setSize(mediaFile.getSize());
			/* defaults */
			media.setActive(1);			
			media.setLang("rs");
			/*
			 * Upload media file on file system
			 * */
			return mediaService.uploadMediaToFileSystem(mediaFile, media.getFileName()) ? media : null;
		}
		System.out.println(media);
		return media;
	}

}
