package org.sobakaisti.mvt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sobakaisti.mvt.dao.PostDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaServiceImpl extends PostServiceImpl<Media> implements MediaService {
	
	@Autowired
	private PostDao<Media> postDao;
		
	@Override
	public boolean uploadMediaToFileSystem(MultipartFile media, String fileName) {
		
		if(media.isEmpty()) return false;
		
		try {			
			 byte[] bytes = media.getBytes();
             Path path = Paths.get(LINUX_UPLOADS_DIR + fileName);
             Files.write(path, bytes);
             return true;
		} catch (Exception e) {
			System.out.println("Greska pri uploadu fajla!");
			return false;
		}		
	}

	@Override
	public boolean fullyRemoveMedia(int id) {
		boolean fullyRemoved = false;
		Media media = postDao.find(id);
		if(media != null) {
			boolean removedFromFilesys = removeMediaFileFromFilesystem(media.getFileName(), LINUX_UPLOADS_DIR);
			if(removedFromFilesys)
				fullyRemoved = postDao.delete(media);
		} else {
			System.err.println("Nisam pronasao objekat medija iz baze, za brisanje. Vracam false!");
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
				
				postDao.save(mediaDetails);
				return true;
			}			
		}
		return false;
	}
}
