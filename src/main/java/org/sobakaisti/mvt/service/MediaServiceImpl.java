package org.sobakaisti.mvt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.MediaDao;
import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.i18n.model.I18nMedia;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.util.CommitResult;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaServiceImpl extends PostServiceImpl<Media, I18nMedia> implements MediaService {
	private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);	
	
	private MediaDao mediaDao;
	@Value( "${media.uploads.path.img}" ) private String imgUploadsPath;
	
	@Autowired
	public MediaServiceImpl(@Qualifier("mediaDaoImpl") PostDao<Media, I18nMedia> postDao) {
		super(postDao);
		this.mediaDao = (MediaDao) postDao;
	}	
		
	@Override
	public boolean uploadMediaToFileSystem(MultipartFile media, String fileName) {
		
		if(media.isEmpty()) return false;
		logger.info("Media datoteku: ["+fileName+"] cuvamo na file systemu.");
		try {			
			 byte[] bytes = media.getBytes();
             Path path = Paths.get(imgUploadsPath + fileName);
             Files.write(path, bytes);
             logger.info("Datoteka ["+fileName+"] uspesno upload-ovana na filesystem.");
             return true;
		} catch (Exception e) {
			logger.warn("Greska pri uploadu datoteke: "+fileName+" uzrok: "+e.getMessage());
			return false;
		}		
	}

	@Override
	public boolean fullyRemoveMedia(int id) {
		boolean fullyRemoved = false;
		Media media = mediaDao.find(id);
		if(media != null) {
			boolean removedFromFilesys = removeMediaFileFromFilesystem(media.getFileName(), imgUploadsPath);
			logger.info("Media datoteka: ["+media.getFileName()+"] "+(removedFromFilesys ? "uklonjena":"nije uklonjena")+" sa file systema.");
			if(removedFromFilesys)
				fullyRemoved = mediaDao.delete(media);			
			logger.info("Media entitet: ["+media.getFileName()+"] "+(fullyRemoved ? "uklonjena":"nije uklonjena")+" iz baze.");
		} else {
			logger.warn("Nisam dohvatio objekat Media za trazeni id:"+id+". Vracam false...");
			fullyRemoved = false;
		}
		return fullyRemoved;
	}

	
	private boolean removeMediaFileFromFilesystem(String fileName, String path) {			
		Path fileToDeletePath = Paths.get(path + fileName);
		try {
			Files.delete(fileToDeletePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateMediaDetails(Media media) {
		if(media != null && media.getId() != 0) {
			Media mediaDetails = findById(media.getId());
			
			if(mediaDetails != null) {
				if(!media.getTitle().isEmpty())
					mediaDetails.setTitle(media.getTitle());
				/* update meda author */
				if(media.getAuthor() != null) {
					Author author = authorDao.getAuthorById(media.getAuthor().getId());
					mediaDetails.setAuthor(author);
				}
				/* update description of media */
				if(!media.getDescriprion().isEmpty()) 
					mediaDetails.setDescriprion(media.getDescriprion());
				
				mediaDao.save(mediaDetails);
				return true;
			}			
		}
		logger.warn("Greska. Nije prosledjen Media objekat za cuvanje. Vracam false...");
		return false;
	}

	@Override
	public Media processAndSaveSubmittedPost(Media post) {
		if(post != null && post.getFile() != null) {
			final MultipartFile mediaFile = post.getFile();
			
			if(mediaFile.getOriginalFilename() != null && !mediaFile.getOriginalFilename().isEmpty()) {
				final String fileName = mediaFile.getOriginalFilename();
				String extensionless = StringUtil.trimExtensionFromFilename(fileName);
				final String extension = StringUtil.extractFilenameExtension(fileName);
				String tmpSlug = StringUtil.makeSlug(extensionless);
				final String slug = addSuffixIfDuplicateExist(tmpSlug);
				/* postavlja user friendly naslov */
				post.setTitle(StringUtil.makeUserFriendlyTitleFromFilename(fileName));
				/* postavlja slug */
				post.setSlug(slug);
				/* original file name */
				post.setFileName(slug + extension);
			}
			/* post date */
			post.setPostDate(Calendar.getInstance());
			/* contentType */
			post.setContentType(mediaFile.getContentType());		
			/* file size */
			post.setSize(mediaFile.getSize());
			/* defaults */
			post.setActive(1);			
			post.setLang(getPostLanguage());
			logger.info("Kreiran objekat Media: "+post);
			
			boolean uploaded = uploadMediaToFileSystem(mediaFile, post.getFileName());
			if(uploaded) {
				Media result = mediaDao.save(post);
				if(result != null) {
					result.setCommited(new Boolean(uploaded));
					result.setCommitMessage(getMessage("media.posted.successful"));
					logger.info("Uspesno sacuvana media datoteka: "+post.getFileName());
					return result;
				}
			} else {
				post.setCommited(new Boolean(uploaded));
				post.setCommitMessage(getMessage("media.posted.failure"));
				logger.warn("Neuspelo cuvanje media datoteke!");
			}
		}
		return post;
	}

	@Override
	public CommitResult commitDelete(int id) {
		boolean deleted = this.delete(id);
		String message = null;
		if(deleted)
			message = getMessage("media.delete.succesful");
		else 
			message = getMessage("media.delete.failure");
		return new CommitResult(deleted, message);
	}
}
