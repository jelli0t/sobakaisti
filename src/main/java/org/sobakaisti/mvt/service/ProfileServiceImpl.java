package org.sobakaisti.mvt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.dao.ProfileDao;
import org.sobakaisti.mvt.models.Profile;
import org.springframework.stereotype.Service;

@Service
public abstract class ProfileServiceImpl<T extends Profile> implements ProfileService<T> {

	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
  	
	private ProfileDao<T> profileDao;		
  
	public ProfileServiceImpl(ProfileDao<T> profileDao) {
		this.profileDao = profileDao;
	}

	@Override
	public boolean removeSocialNetwork(int snid) {
		return profileDao.removeSocialNetwork(snid);
	}
	
	@Override
	public T saveOrUpdateProfile(T t) {
		if(t != null)
			return profileDao.saveOrUpdateProfile(t);
		return null;
	}
	
	@Override
	public CommitResult commitProfilSocialNetworkDeletion(int profileId, int socnetId) {
		boolean removed = profileDao.removeProfilesSocialNetwork(int socnetId, int profileId);
		return new CommitResult(removed, "");
	}
  
}
