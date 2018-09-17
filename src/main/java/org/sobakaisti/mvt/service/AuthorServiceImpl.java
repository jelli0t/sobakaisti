/**
 * 
 */
package org.sobakaisti.mvt.service;

import java.util.List;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.dao.ProfileDao;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.AuthorProfile;
import org.sobakaisti.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author jelles
 *
 */
@Service
public class AuthorServiceImpl extends ProfileServiceImpl<AuthorProfile> implements AuthorService {

	@Autowired
	private AuthorDao authorDao;
		
	@Autowired
	public AuthorServiceImpl(@Qualifier("authorProfileDao") ProfileDao<AuthorProfile> postDao) {
		super(postDao);
	}

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

	@Override
	public Author findFull(String slug) {
		return authorDao.findFull(slug);
	}
	
	@Override
	public Author findFull(int id) {
		return authorDao.findFull(id);
	}
}
