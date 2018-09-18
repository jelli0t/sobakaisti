package org.sobakaisti.mvt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.ProfileDao;
import org.sobakaisti.mvt.models.Profile;
import org.springframework.stereotype.Service;

@Service
public abstract class ProfileServiceImpl<T extends Profile> implements ProfileService<T> {

	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
  	
	private ProfileDao<T> postDao;		
  
	public ProfileServiceImpl(ProfileDao<T> postDao) {
		this.postDao = postDao;
	}

	@Override
	public boolean removeSocialNetwork(int snid) {
		return postDao.removeSocialNetwork(snid);
	}
	
	@Override
	public T saveOrUpdateProfile(T t) {
		if(t != null)
			return postDao.saveOrUpdateProfile(t);
		return null;
	}
  
}
