/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Korisnik
 *
 */
@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;
	
	@Override
	public List<Author> findAll() {
		return authorDao.getAllAuthors();
	}
	
	@Override
	public List<String> getAuthorsManifestQuotes() {			
		return PropertiesUtil.quotes.getBundleValues(PropertiesUtil.MANIFEST_QUOTES_BUNDLE_KEY);
	}

	@Override
	public Author findBySlug(String slug) {
		return authorDao.findAuthorBySlug(slug);
	}

}
