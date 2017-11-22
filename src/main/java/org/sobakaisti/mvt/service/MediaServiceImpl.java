package org.sobakaisti.mvt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.MediaDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaServiceImpl extends PostServiceImpl<Media> implements MediaService {
	
	@Autowired
	private MediaDao mediaDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);
		
	@Override
	public boolean uploadMediaToFileSystem(MultipartFile media, String fileName) {
		
		if(media.isEmpty()) return false;
		logger.info("Media datoteku: {"+fileName+"} cuvamo na file systemu.");
		try {			
			 byte[] bytes = media.getBytes();
             Path path = Paths.get(LINUX_UPLOADS_DIR + fileName);
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
			boolean removedFromFilesys = removeMediaFileFromFilesystem(media.getFileName(), LINUX_UPLOADS_DIR);
			logger.info("Media datoteka: {"+media.getFileName()+"} "+(removedFromFilesys ? "uklonjena":"nije uklonjena")+" sa file systema.");
			if(removedFromFilesys)
				fullyRemoved = mediaDao.delete(media);			
			logger.info("Media entitet: {"+media.getFileName()+"} "+(fullyRemoved ? "uklonjena":"nije uklonjena")+" iz baze.");
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
			Media mediaDetails = postDao.find(media.getId());
			
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
		return false;
	}
}
