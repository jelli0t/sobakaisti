package org.sobakaisti.mvt.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sobakaisti.mvt.models.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaServiceImpl extends PostServiceImpl<Media> implements MediaService {
	
	
	@Override
	public boolean uploadMediaToFileSystem(MultipartFile media, String fileName) {
		
		if(media.isEmpty()) return false;
		
		try {
			
			 byte[] bytes = media.getBytes();
             Path path = Paths.get(UPLOADS_DIR + fileName);
             Files.write(path, bytes);
             System.out.println("Uspesno uploadovan file: "+path.toString());
             return true;
		} catch (Exception e) {
			System.out.println("Greska pri uploadu fajla!");
			return false;
		}		
	}

}
